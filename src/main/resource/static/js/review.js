const usuarioId = localStorage.getItem("usuarioId");
const filmeId = localStorage.getItem("filmeId");

async function enviar() {
    const nota = document.getElementById("nota").value;
    const comentario = document.getElementById("comentario").value;

    await fetch(`/reviews?usuarioId=${usuarioId}&filmeId=${filmeId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nota,
            comentario,
            onboarding: false
        })
    });

    alert("Avaliação salva!");
    window.location.href = "/filmes.html";
}
