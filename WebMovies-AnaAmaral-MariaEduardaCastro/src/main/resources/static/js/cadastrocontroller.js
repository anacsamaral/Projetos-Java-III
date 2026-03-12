function cadastrarFilmePoster(){
    const formulario = document.forms[0];
    fetch("http://localhost:8080/apis/add-movie-poster",
        { method: 'POST', body: new FormData(formulario)})
        .then(resposta => resposta.json())
        .then(json => {
            alert("Filme " + json.title + " cadastrado com sucesso!");
            formulario.reset();
        })
        .catch(erro => alert("Problemas ao cadastrar o filme"))
}

function cadastrarFilme(){
    const formularioFilme = document.forms[0];
    if(formularioFilme.titulo.value.length === 0){
        alert("Preencha todos os campos");
    }
    else{
        const filme = {};
        filme.title = formularioFilme.titulo.value;
        filme.year = formularioFilme.ano.value;
        filme.category = formularioFilme.genero.value;

        if(enviarFilme(JSON.stringify(filme))) {
            formularioFilme.reset();
        }
    }
}

function enviarFilme(dadosFilme){
    const opcoesRequisicao = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: dadosFilme
    };
    fetch("http://localhost:8080/apis/add-movie", opcoesRequisicao)
        .then(resposta => resposta.json())
        .then(json => {
            alert("Filme " + json.title + " cadastrado com sucesso!");
            return true;
        })
        .catch(erro => {
            alert("Problemas ao cadastrar o filme");
            return false;
        })
}