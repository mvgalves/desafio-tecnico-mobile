# 📱 Desafio Técnico – Estágio em Desenvolvimento Mobile

Este projeto foi desenvolvido como parte do desafio técnico para a vaga de **Estágio em Desenvolvimento Mobile**.  
O objetivo é criar um aplicativo Android simples, funcional e bem estruturado, atendendo aos requisitos definidos no PDF do desafio.

---

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **Arquitetura:** Activities + SharedPreferences para persistência simples
- **Layouts:** ConstraintLayout + NestedScrollView
- **IDE:** Android Studio

---

## 📂 Estrutura do Projeto

app/
├── java/com/example/desafio_tecnico_mobile/  
│ ├── SplashScreen.kt # Tela de abertura (Splash)  
│ ├── LoginActivity.kt # Tela de Login  
│ ├── RegisterActivity.kt # Tela de Cadastro  
│ ├── MainActivity.kt # Tela Principal  
│  
└── res/layout/  
├── activity_splash_screen.xml  
├── activity_login.xml  
├── activity_register.xml  
├── activity_main.xml  
  

---

## 🖼️ Funcionalidades

### Splash Screen
- Exibe logotipo e nome do app por alguns segundos.
- Redireciona automaticamente para a tela correta:
  - Vai para **Login** se não houver usuário logado.
  - Vai para **Tela Principal** se já houver sessão ativa.

### Tela de Login
- Campos de **usuário/e-mail** e **senha**.
- Botão **Entrar**.
- Botão **Criar conta**.
- Validações:
  - Não permite campos vazios.
  - Só permite login de usuário existente.
  - Verifica senha antes de logar.
- Extras:
  - Alternar visibilidade da senha (ícone olho).
  - Link "Esqueci minha senha" (apenas ilustrativo).

### Tela de Cadastro
- Campos para **nome de usuário, e-mail, senha e confirmação de senha**.
- Botão **Cadastrar**.
- Validações:
  - Todos os campos obrigatórios.
  - E-mail válido.
  - Senha com no mínimo 6 caracteres.
  - Confirmação de senha.
  - Não permite cadastrar usuário ou e-mail duplicado.
- Após cadastro, retorna para a tela de login.

### Tela Principal
- Exibe dados do usuário logado (**nome e e-mail**).
- Botão **Deslogar**:
  - Apaga a sessão ativa.
  - Retorna para a tela de login.

---

## ⚙️ Persistência de Dados
- Usuários são salvos localmente via **SharedPreferences**.
- A sessão do usuário permanece ativa até logout:
  - Se o app for fechado e reaberto, o usuário continua logado.
  - Logout remove o usuário ativo e retorna à tela de login.

---

## 👨‍💻 Autor
**Marcus Vinicius Garcia Alves**  
[LinkedIn]([https://www.linkedin.com/](https://www.linkedin.com/in/marcus-vinicius-garcia-alves/)) | [GitHub]((https://github.com/mvgalves))
