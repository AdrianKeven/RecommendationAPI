const usuarioId = localStorage.getItem("usuarioId");
let avaliacoes = [];

// 1️⃣ Buscar filmes do onboarding
async function carregarFilmes() {
    const response = await fetch("/onboarding/filmes?limite=10");
    const filmes = await response.json();

    const lista = document.getElementById("listaFilmes");
    lista.innerHTML = "";

    filmes.forEach(filme => {
        const card = document.createElement("div");
        card.className = "card-filme";

        card.innerHTML = `
            <h3>${filme.titulo}</h3>
            <p>${filme.genero ?? ""}</p>

            <label>Nota (1 a 5)</label><br>
            <input 
              type="number" 
              min="1" 
              max="5"
              onchange="registrarAvaliacao(${filme.id}, this.value)"
            >
        `;

        lista.appendChild(card);
    });
}

// 2️⃣ Registrar nota no array
function registrarAvaliacao(filmeId, nota) {
    // remove se já existir
    avaliacoes = avaliacoes.filter(a => a.filmeId !== filmeId);

    avaliacoes.push({
        filmeId: filmeId,
        nota: Number(nota)
    });
}

// 3️⃣ Enviar tudo para o backend
async function enviarAvaliacoes() {
    if (avaliacoes.length === 0) {
        alert("Avalie pelo menos um filme 🙂");
        return;
    }

    await fetch(`/onboarding/avaliacoes?usuarioId=${usuarioId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(avaliacoes)
    });

    alert("Onboarding concluído!");
    window.location.href = "/onboarding.js";
}

// carregar automaticamente
carregarFilmes();
