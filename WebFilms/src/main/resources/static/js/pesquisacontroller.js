function pesquisarFilmesKW() {
    const keyword = document.getElementById("keyword").value;
    const resultado = document.getElementById("resultado");
    fetch("http://localhost:8080/apis/list-keyword?keyword="+keyword)
        .then(response => {
            if(response.status === 200)
                response.json()
                    .then(json => {resultado.innerHTML = gerarLinhas(json)})
            else{
                alert("Não há resultados");}
        })
    .catch(error => {resultado.innerHTML = error})
}

function gerarLinhas(jsonList) {
    let dados = "";
    for(let movie of jsonList) {
        dados += ``
    }
}