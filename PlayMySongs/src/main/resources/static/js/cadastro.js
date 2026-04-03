document.addEventListener('DOMContentLoaded', () => {
    // 1. Carrega os estilos
    fetch('http://localhost:8080/apis/get-music-styles')
        .then(res => res.json())
        .then(estilos => {
            const select = document.getElementById('estiloMusical');
            select.innerHTML = '<option value="">Selecione um estilo...</option>';
            estilos.forEach(est => {
                select.innerHTML += `<option value="${est.nome}">${est.nome}</option>`;
            });
        }).catch(err => console.error(err));

    // 2. Lida com o Envio
    document.getElementById('formCadastro').addEventListener('submit', async (e) => {
        e.preventDefault();
        const divMensagem = document.getElementById('mensagem');
        const arquivo = document.getElementById('formFile').files[0];

        if (arquivo.type !== 'audio/mpeg' && arquivo.type !== 'audio/ogg') {
            divMensagem.innerHTML = '<div class="alert alert-danger">Apenas MP3 ou OGG.</div>';
            return;
        }

        const formData = new FormData();
        formData.append('nome', document.getElementById('tituloMusica').value);
        formData.append('artista', document.getElementById('artista').value);
        formData.append('estilo', document.getElementById('estiloMusical').value);
        formData.append('file', arquivo);

        divMensagem.innerHTML = '<div class="alert alert-info">Enviando para o servidor...</div>';

        try {
            const response = await fetch('http://localhost:8080/apis/music-upload', { method: 'POST', body: formData });
            if (!response.ok) throw new Error(await response.text());

            const json = await response.json();
            divMensagem.innerHTML = `<div class="alert alert-success">Sucesso! Salvo como: <b>${json.musicFileName}</b></div>`;
            document.getElementById('formCadastro').reset();
        } catch (error) {
            divMensagem.innerHTML = `<div class="alert alert-danger">${error.message}</div>`;
        }
    });
});