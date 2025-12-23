// ===============================
// LISTAR FILMES
// ===============================
async function carregarFilmesPaginaFilmes() {
    try {
        const res = await fetch("/filmes");

        if (!res.ok) {
            throw new Error("Erro ao buscar filmes");
        }

        const filmes = await res.json();
        const lista = document.getElementById("listaFilmes");
        lista.innerHTML = "";

        filmes.forEach(filme => {
            const div = document.createElement("div");
            const baseUrl = window.location.origin;
            div.className = "card-filme";

            div.innerHTML = `
                <img 
                  src="${filme.imagemUrl ?? '/assets/filmes/sem-imagem.jpg'}"
                  alt="${filme.titulo}"
                  class="img-filme"
                  onerror="this.onerror=null; this.src='/assets/filmes/sem-imagem.jpg';"
                />

                <h3>${filme.titulo}</h3>
                <p>${filme.genero ?? ""}</p>
                <button onclick="avaliar(${filme.id})">
                    Avaliar
                </button>
            `;

            lista.appendChild(div);
        });

    } catch (error) {
        console.error(error);
        alert("Não foi possível carregar os filmes");
    }
}

// ===============================
// CRIAR FILME
// ===============================
async function criarFilme() {
    const titulo = document.getElementById("titulo").value.trim();
    const descricao = document.getElementById("descricao").value.trim();
    const diretor = document.getElementById("diretor").value.trim();
    const genero = document.getElementById("genero").value.trim();
    const anoLancamento = document.getElementById("anoLancamento").value;
    const imagem = document.getElementById("imagem").files[0];

    if (!titulo || !diretor || !genero) {
        alert("Preencha os campos obrigatórios");
        return;
    }

    const formData = new FormData();
    formData.append("titulo", titulo);
    formData.append("descricao", descricao);
    formData.append("diretor", diretor);
    formData.append("anoLancamento", anoLancamento);
    formData.append("genero", genero);

    if (imagem) {
        formData.append("imagem", imagem);
    }

    const res = await fetch("/filmes", {
        method: "POST",
        body: formData
    });

    if (!res.ok) {
        alert("Erro ao criar filme");
        return;
    }

    carregarFilmesPaginaFilmes();
}

// ===============================
// AVALIAR FILME
// ===============================
function avaliar(filmeId) {
    localStorage.setItem("filmeId", filmeId);
    window.location.href = "/review.html";
}

// ===============================
// INIT
// ===============================
document.addEventListener("DOMContentLoaded", () => {
    carregarFilmesPaginaFilmes();
});
