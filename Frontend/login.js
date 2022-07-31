const url = "http://localhost:3000";

document.getElementById("loginButton").onclick = loginFunction;

async function loginFunction() {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let user = {
    username: username,
    password: password,
  };
  if (username === "" || password === "") {
    alert("Please enter a username and password");
  }
  let config = {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include",
  };
  let response = await fetch(url + "/login", config);

  if (response.status === 202) {
    let data = await response.json();
    console.log(data);
    localStorage.setItem("user", JSON.stringify(data));
    let user = JSON.parse(localStorage.getItem("user"));
    if (user.user_role_id === 2) {
      window.location.href = "./manager.html";
    } else {
      window.location.href = "./employee.html";
    }
  }
}
