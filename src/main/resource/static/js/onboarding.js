const usuarioId = localStorage.getItem("usuarioId");
let avaliacoes = [];

// ===============================
// CARREGAR FILMES
// ===============================
async function carregarFilmesOnboarding() {
    const response = await fetch("/onboarding/filmes?limite=10");
    const filmes = await response.json();

    const lista = document.getElementById("listaFilmes");
    lista.innerHTML = "";

    filmes.forEach(filme => {
        const card = document.createElement("div");
        card.className = "card-filme";

        card.innerHTML = `
          <img 
            src="${filme.imagemUrl || '/assets/filmes/sem-imagem.jpg'}"
            alt="${filme.titulo}"
            class="img-filme"
            onerror="this.onerror=null; this.src='/assets/filmes/sem-imagem.jpg';"
          >
        
          <h3>${filme.titulo}</h3>
          <div class="genero-filme">
          <p>${filme.genero ?? ""}</p>
          </div>
          <label>Nota (1 a 5)</label>
          <input 
            type="number"
            min="1"
            max="5"
            id="nota-${filme.id}"
          >
        
          <label>Comentário</label>
          <textarea
            id="comentario-${filme.id}"
            placeholder="Escreva seu comentário">
          </textarea>
        
          <button onclick="registrarAvaliacao(${filme.id})">
            Avaliar
          </button>
        `;


        lista.appendChild(card);
    });
}

// ===============================
// REGISTRAR AVALIAÇÃO
// ===============================
function registrarAvaliacao(filmeId) {
    const nota = Number(document.getElementById(`nota-${filmeId}`).value);
    const comentario = document.getElementById(`comentario-${filmeId}`).value.trim();

    if (!nota || nota < 1 || nota > 5) {
        alert("Informe uma nota válida (1 a 5)");
        return;
    }

    avaliacoes = avaliacoes.filter(a => a.filmeId !== filmeId);

    avaliacoes.push({
        filmeId,
        nota,
        comentario
    });

    alert("Avaliação registrada!");
}

// ===============================
// ENVIAR AVALIAÇÕES
// ===============================
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
    window.location.href = "/recomendacao.html";
}

// INIT
carregarFilmesOnboarding();
