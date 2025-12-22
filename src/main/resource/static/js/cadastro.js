async function cadastro() {
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const erro = document.getElementById("erro");

    erro.textContent = "";

    if (!email || !senha) {
        erro.textContent = "Email e senha são obrigatórios";
        return;
    }

    try {

        if (!nome) {
            erro.textContent = "Informe o nome para cadastro";
            return;
        }

        await criarUsuario(nome, email, senha);

    } catch (e) {
        erro.textContent = "Erro ao conectar com o servidor";
    }
}

async function criarUsuario(nome, email, senha) {
    const response = await fetch("/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email, senha })
    });

    if (!response.ok) {
        document.getElementById("erro").textContent = "Erro ao criar usuário";
        return;
    }

    const usuario = await response.json();
    salvarUsuario(usuario);
}

function salvarUsuario(usuario) {
    localStorage.setItem("usuarioId", usuario.id);
    localStorage.setItem("usuarioNome", usuario.nome);
    localStorage.setItem("usuarioEmail", usuario.email);

    window.location.href = "/onboarding.html";
}
