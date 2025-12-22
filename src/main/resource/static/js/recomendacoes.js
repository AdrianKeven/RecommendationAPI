const usuarioId = localStorage.getItem("usuarioId");

async function carregar() {
    const res = await fetch(`/recomendacoes/${usuarioId}`);
    const filmes = await res.json();

    const lista = document.getElementById("lista");
    lista.innerHTML = "";

    filmes.forEach(f => {
        const div = document.createElement("div");
        div.className = "card-filme";
        div.innerHTML = `<h3>${f.titulo}</h3>`;
        lista.appendChild(div);
    });
}

carregar();
