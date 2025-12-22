async function carregarFilmes() {
    const res = await fetch("/filmes");
    const filmes = await res.json();

    const lista = document.getElementById("listaFilmes");
    lista.innerHTML = "";

    filmes.forEach(f => {
        const div = document.createElement("div");
        div.className = "card-filme";
        div.innerHTML = `
      <h3>${f.titulo}</h3>
      <button onclick="avaliar(${f.id})">Avaliar</button>
    `;
        lista.appendChild(div);
    });
}

async function criarFilme() {
    const titulo = document.getElementById("titulo").value;

    await fetch("/filmes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ titulo })
    });

    carregarFilmes();
}

function avaliar(id) {
    localStorage.setItem("filmeId", id);
    window.location.href = "/review.html";
}

carregarFilmes();
