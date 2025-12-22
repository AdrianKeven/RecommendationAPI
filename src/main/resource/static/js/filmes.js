// ===============================
// LISTAR FILMES
// ===============================
async function carregarFilmes() {
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
            div.className = "card-filme";

            div.innerHTML = `
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

    const valorAno = document.getElementById("anoLancamento").value;
    const anoLancamento = Number(valorAno);

    // validações básicas
    if (!titulo || !diretor || !genero) {
        alert("Preencha os campos obrigatórios");
        return;
    }

    if (isNaN(anoLancamento)) {
        alert("Ano de lançamento inválido");
        return;
    }

    try {
        const res = await fetch("/filmes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                titulo,
                descricao,
                diretor,
                anoLancamento,
                genero
            })
        });

        if (!res.ok) {
            throw new Error("Erro ao criar filme");
        }

        // limpa formulário
        document.getElementById("titulo").value = "";
        document.getElementById("descricao").value = "";
        document.getElementById("diretor").value = "";
        document.getElementById("anoLancamento").value = "";
        document.getElementById("genero").value = "";

        // recarrega lista
        carregarFilmes();

    } catch (error) {
        console.error(error);
        alert("Erro ao salvar filme");
    }
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
    carregarFilmes();
});
