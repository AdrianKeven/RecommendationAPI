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

            <label>Nota (1 a 5)</label>
            <br>
            <input 
              type="number" 
              min="1" 
              max="5"
              id="nota-${filme.id}"
            >
            <br>
            
            <label>Comentário</label>
            <br>
            <textarea
             id="comentario-${filme.id}"
             rows="10"
             cols="30"
            placeholder="Escreva seu comentário"></textarea>
            <br>
            <input 
              type="button" 
              value="Avaliar"
              onclick="registrarAvaliacao(${filme.id})"
            >
            <br>
        `;

        lista.appendChild(card);
    });
}

// 2️⃣ Registrar avaliação
function registrarAvaliacao(filmeId) {
    const notaInput = document.getElementById(`nota-${filmeId}`);
    const comentarioInput = document.getElementById(`comentario-${filmeId}`);

    const nota = Number(notaInput.value);
    const comentario = comentarioInput.value;

    if (!nota || nota < 1 || nota > 5) {
        alert("Informe uma nota válida (1 a 5)");
        return;
    }

    // remove se já existir
    avaliacoes = avaliacoes.filter(a => a.filmeId !== filmeId);

    avaliacoes.push({
        filmeId,
        nota,
        comentario
    });

    alert("Avaliação registrada!");
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
    window.location.href = "/recomendacao.html";
}

    // 4️⃣ Carregar automaticamente
    carregarFilmes();
