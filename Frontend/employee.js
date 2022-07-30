const url = "http://localhost:3000";
let user = JSON.parse(localStorage.getItem("user"));

document.getElementById("welcomeMessage").innerHTML =
  "Welcome " + user.user_first_name + " " + user.user_last_name;

document.getElementById("welcomeMessage").onload = getUserReimbursements();
document.getElementById("submitButton").onclick = insertReimbursement;
document.getElementById("logoutButton").onclick = logout;

function logout() {
  localStorage.removeItem("user");
  window.location.href = "./index.html";
}

async function insertReimbursement() {
  let reimb_amount = document.getElementById("reimb_amount").value;
  if (reimb_amount === "") {
    alert("Please enter an amount");
  } else {
    let reimbursement = {
      reimb_amount: reimb_amount,
      reimb_author: user.ers_users_id,
      reimb_status_id: 1,
      reimb_type_id: document.querySelector('input[name="reimb_type"]:checked')
        .value,
    };
    let config = {
      method: "POST",
      body: JSON.stringify(reimbursement),
      credentials: "include",
    };
    let response = await fetch(url + "/reimbursements", config);
    if (response.status === 202) {
      window.location.reload();
    } else {
      alert("Insert Reimbursement Failed");
    }
  }
}

async function getUserReimbursements() {
  console.log("working");
  let response = await fetch(url + "/reimbursements/" + user.ers_users_id);
  if (response.status === 200) {
    let data = await response.json();
    console.log(data);

    for (let reimbursement of data) {
      let row = document.createElement("tr");
      let cell = document.createElement("td");
      cell.innerHTML = reimbursement.reimb_id;
      row.appendChild(cell);
      cell = document.createElement("td");
      cell.innerHTML = reimbursement.reimb_amount;
      row.appendChild(cell);
      cell = document.createElement("td");
      cell.innerHTML = reimbursement.reimb_submitted;
      row.appendChild(cell);
      cell = document.createElement("td");
      cell.innerHTML =
        reimbursement.author.user_first_name +
        ", " +
        reimbursement.author.user_last_name;
      row.appendChild(cell);
      cell = document.createElement("td");
      cell.innerHTML = reimbursement.reimb_status.reimb_status;
      row.appendChild(cell);
      cell = document.createElement("td");
      cell.innerHTML = reimbursement.reimb_type.reimb_type;
      row.appendChild(cell);

      document.getElementById("tableBody").appendChild(row);
    }
  } else {
    alert("GET USER REIMBURSEMENTS FAILED");
  }
}
