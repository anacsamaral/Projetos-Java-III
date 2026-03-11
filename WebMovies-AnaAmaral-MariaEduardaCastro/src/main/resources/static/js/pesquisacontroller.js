function pesquisarFilmesKW(){
    const keyword=document.getElementById('keyword').value;
    const resultado=document.getElementById('resultado');
    fetch("http://localhost:8080/apis/list-keyword?keyword="+keyword)
        .then(response => {
            if(response.status===200) {
                return response.json()
                    .then(json => {
                        resultado.innerHTML = gerarLinhas(json);
                    });
            }
            else{alert("Não há resultados");}
        })
        .catch(error => {resultado.innerHTML=error})
}

function gerarLinhas(jsonList) {
    if (!jsonList || jsonList.length === 0) {
        return '<tr><td colspan="4">Nenhum filme encontrado</td></tr>';
    }

    let dados = "";
    for (let movie of jsonList) {
        // Verifica se o filme tem thumbnail ou poster
        let imgSrc = movie.thumb ? `/thumbs/${movie.thumb}` : (movie.poster ? `/posters/${movie.poster}` : null);

        // Se a imagem existir, gera a tag IMG que clica e abre o popover (Itens c e d)
        let tdImagem = imgSrc
            ? `<td>
                 <img src="${imgSrc}" style="width:50px; height:auto; border-radius:4px; border: 1px solid #ddd; cursor:pointer;" 
                      onclick="abrirPopover('/posters/${movie.poster}')" title="Clique para ampliar">
               </td>`
            : `<td>Sem imagem</td>`;

        dados += `<tr>
                    <td>${movie.title}</td>
                    <td>${movie.year}</td>
                    <td>${movie.category?.nome || ''}</td>
                    ${tdImagem}
                  </tr>`;
    }
    return dados;
}

// --- FUNÇÕES DO POPOVER ---
// CORRIGIDO: Removido o espaço do parâmetro "caminhoPosterOriginal"
function abrirPopover(caminhoPosterOriginal) {
    let popover = document.getElementById('popover-poster');
    let imagemPopover = document.getElementById('imagem-popover');
    imagemPopover.src = caminhoPosterOriginal; // Carrega a imagem grande
    popover.style.display = 'flex'; // Mostra a janela escura
}

function fecharPopover() {
    let popover = document.getElementById('popover-poster');
    popover.style.display = 'none'; // Esconde a janela
}

// --- RESTO DAS SUAS FUNÇÕES ORIGINAIS ---
function pesquisarFilmesGenero(){
    const genero=document.getElementById('genero').value;
    const resultado =document.getElementById('resultado');
    fetch("http://localhost:8080/apis/list-genre/"+genero)
        .then(response =>{
            if(response.status ===200){
                return response.json()
                    .then(json =>{
                        resultado.innerHTML=gerarLinhas(json);
                    });
            }
            else{alert("Não há resultados");}
        })
        .catch(error =>{resultado.innerHTML=error})
}

function pesquisarFilmesAno(){
    const anoInicio=document.getElementById('anoInicio').value;
    const anoFim=document.getElementById('anoFim').value;
    const resultado =document.getElementById('resultado');
    fetch(`http://localhost:8080/apis/list-year/${anoInicio}/${anoFim}`)
        .then(response =>{
            if(response.status ===200){
                return response.json()
                    .then(json =>{
                        resultado.innerHTML=gerarLinhas(json);
                    });
            }
            else{alert("Não há resultados");}
        })
        .catch(error =>{resultado.innerHTML=error})
}