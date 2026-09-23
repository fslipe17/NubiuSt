const arquivo = document.getElementById("arquivo");

const areaUpload = document.querySelector(".area-upload");

const nomeArquivo = document.getElementById("nome-arquivo");

areaUpload.addEventListener("dragover", function(event) {
    event.preventDefault();

    areaUpload.classList.add("arrastando");
});
areaUpload.addEventListener("dragleave", function () {
    areaUpload.classList.remove("arrastando");
});

areaUpload.addEventListener("drop", function (event) {
    event.preventDefault();

    areaUpload.classList.remove("arrastando");

    const arquivoSolto = event.dataTransfer.files[0];

    nomeArquivo.textContent = arquivoSolto.name;

    const formulario = new FormData();

    formulario.append("arquivo", arquivoSolto);

    fetch("http://localhost:8080/api/arquivos", {
        method: "POST",
        body: formulario
    });
});

arquivo.addEventListener("change", async function() {

    const arquivoSelecionado = arquivo.files[0];

    nomeArquivo.textContent = arquivoSelecionado.name;

    console.log("Nome mostrado:", nomeArquivo.textContent);

    const formulario = new FormData();

    formulario.append("arquivo", arquivoSelecionado);

    console.log("Tentando enviar o arquivo para o servidor...");

    // const resposta = await fetch("http://127.0.0.1:8080/api/arquivos", {
    //     method: "POST",
    //     body: formulario
    // });

    // const resultado = await resposta.text();

    // console.log(resultado);
});