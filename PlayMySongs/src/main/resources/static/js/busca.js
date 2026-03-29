document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('formBusca').addEventListener('submit', async (e) => {
        e.preventDefault();
        const keyword = document.getElementById('keyword').value;
        const divResultados = document.getElementById('resultadosBusca');

        divResultados.innerHTML = '<div class="col-12"><div class="alert alert-info text-center">Buscando na biblioteca...</div></div>';

        try {
            const response = await fetch(`http://localhost:8080/apis/find-musics?keyword=${keyword}`);
            if (!response.ok) throw new Error(await response.text());

            const musicas = await response.json();
            divResultados.innerHTML = '';

            if(musicas.length === 0) {
                divResultados.innerHTML = '<div class="col-12"><div class="alert alert-warning text-center">Nenhuma música encontrada.</div></div>';
                return;
            }

            musicas.forEach(musica => {
                if (!musica.musicFileName) return;

                const urlAudio = `http://localhost:8080/uploads/${musica.musicFileName}`;
                const tipo = musica.musicFileName.endsWith('.ogg') ? 'audio/ogg' : 'audio/mpeg';

                divResultados.innerHTML += `
                    <div class="col-md-6 col-lg-6">
                        <div class="card glass-card h-100 border-0 shadow-sm" style="border-left: 4px solid var(--neon-purple) !important;">
                            <div class="card-body">
                                <h5 class="card-title text-light">${musica.titulo}</h5>
                                <h6 class="card-subtitle mb-3 text-secondary">${musica.artista} • ${musica.estilo}</h6>
                                <audio controls class="w-100 mt-2" controlsList="nodownload">
                                    <source src="${urlAudio}" type="${tipo}">
                                </audio>
                            </div>
                        </div>
                    </div>
                `;
            });
        } catch (error) {
            divResultados.innerHTML = `<div class="col-12"><div class="alert alert-danger text-center">${error.message}</div></div>`;
        }
    });
});