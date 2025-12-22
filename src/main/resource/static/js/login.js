async function login() {

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const erro = document.getElementById("erro");

    erro.textContent = "";

    try {
        const res = await fetch("/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, senha })
        });

        if (!res.ok) {
            erro.textContent = "Email ou senha inválidos";
            return;
        }

        const usuario = await res.json();
        salvarUsuario(usuario);

    } catch (e) {
        erro.textContent = "Erro ao conectar com o servidor";
    }

    window.location.href = "/onboarding.html";
}
