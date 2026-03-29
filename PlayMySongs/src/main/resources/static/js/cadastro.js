document.addEventListener('DOMContentLoaded', () => {

    // 1. CARREGAR ESTILOS MUSICAIS DA API
    fetch('http://localhost:8080/apis/get-music-styles')
        .then(response => response.json())
        .then(estilos => {
            const selectEstilo = document.getElementById('estiloMusical');
            selectEstilo.innerHTML = '<option value="" selected>Selecione um estilo:</option>';

            // Preenche o combobox com os estilos vindos do MongoDB
            estilos.forEach(estilo => {
                selectEstilo.innerHTML += `<option value="${estilo.nome}">${estilo.nome}</option>`;
            });
        })
        .catch(error => console.error('Erro ao carregar estilos:', error));

    // 2. ENVIAR FORMULÁRIO (Upload da Música)
    document.getElementById('formCadastro').addEventListener('submit', async function(event) {
        event.preventDefault(); // Evita que a página recarregue

        const divMensagem = document.getElementById('mensagem');
        divMensagem.innerHTML = '<div class="alert alert-info mt-3">Enviando...</div>';

        const arquivoInput = document.getElementById('formFile');
        const arquivo = arquivoInput.files[0];

        // Validação JS solicitada no enunciado
        if (arquivo.type !== 'audio/mpeg' && arquivo.type !== 'audio/ogg') {
            divMensagem.innerHTML = '<div class="alert alert-danger mt-3">Erro: O arquivo deve ser no formato MP3 ou OGG.</div>';
            return;
        }

        // Monta o FormData para enviar textos e arquivo juntos
        const formData = new FormData();
        formData.append('nome', document.getElementById('tituloMusica').value);
        formData.append('estilo', document.getElementById('estiloMusical').value);
        formData.append('artista', document.getElementById('artista').value);
        formData.append('file', arquivo);

        try {
            const response = await fetch('http://localhost:8080/apis/music-upload', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                const errorMsg = await response.text();
                throw new Error(errorMsg);
            }

            const musicaSalva = await response.json();

            // Mostra mensagem de sucesso com o nome gerado pelo Java
            divMensagem.innerHTML = `<div class="alert alert-success mt-3">Sucesso! Arquivo gerado: <b>${musicaSalva.musicFileName}</b></div>`;

            // Limpa o formulário para o próximo cadastro
            document.getElementById('formCadastro').reset();

        } catch (error) {
            divMensagem.innerHTML = `<div class="alert alert-danger mt-3">Erro no servidor: ${error.message}</div>`;
        }
    });

});