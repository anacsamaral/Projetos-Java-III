document.addEventListener('DOMContentLoaded', () => {

    document.getElementById('formBusca').addEventListener('submit', async function(event) {
        event.preventDefault(); // Impede a página de recarregar

        const keyword = document.getElementById('keyword').value;
        const divResultados = document.getElementById('resultadosBusca');

        // Mostra uma mensagem visual de que está buscando
        divResultados.innerHTML = '<div class="alert alert-light text-center">Buscando na biblioteca... 🎧</div>';

        try {
            // Chama a API passando a palavra-chave via URL (Query Param)
            const response = await fetch(`http://localhost:8080/apis/find-musics?keyword=${keyword}`);

            // Se o Java retornou "badRequest" (ex: não encontrou nenhuma música ou a lista veio vazia)
            if (!response.ok) {
                const errorMsg = await response.text();
                divResultados.innerHTML = `<div class="alert alert-warning text-center">${errorMsg}</div>`;
                return;
            }

            // Transforma a resposta do servidor em um Array JSON de Objetos
            const musicas = await response.json();

            // Limpa a div de "Buscando..." para podermos desenhar os resultados
            divResultados.innerHTML = '';

            // Percorre cada música encontrada e cria um "card" com o player para ela
            musicas.forEach(musica => {
                if (!musica.musicFileName)
                    return;

                // Identifica se é mp3 ou ogg para colocar na tag <source>
                const tipoAudio = musica.musicFileName.endsWith('.ogg') ? 'audio/ogg' : 'audio/mpeg';

                // O Spring Boot serve os arquivos da pasta "static" automaticamente.
                // Como salvamos na pasta "uploads", a URL fica assim:
                const urlAudio = `http://localhost:8080/uploads/${musica.musicFileName}`;

                // Cria o HTML do card. O uso do acento grave (`) permite quebrar linhas facilmente!
                const cardHTML = `
                    <div class="card mb-3 border-0 shadow-sm" style="background: rgba(255, 255, 255, 0.95); border-radius: 15px;">
                        <div class="card-body">
                            <h5 class="card-title text-purple">🎵 ${musica.titulo}</h5>
                            <h6 class="card-subtitle mb-3 text-muted">${musica.artista} • ${musica.estilo}</h6>
                            
                            <audio controls class="w-100">
                                <source src="${urlAudio}" type="${tipoAudio}">
                                Seu navegador não suporta a tag de áudio.
                            </audio>
                        </div>
                    </div>
                `;

                // Adiciona esse card na tela
                divResultados.innerHTML += cardHTML;
            });

        } catch (error) {
            divResultados.innerHTML = `<div class="alert alert-danger">Erro ao conectar com o servidor: ${error.message}</div>`;
        }
    });

});