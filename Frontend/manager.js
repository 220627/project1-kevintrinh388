const url = "http://localhost:3000";
let user = JSON.parse(localStorage.getItem("user"));

document.getElementById("welcomeMessage").innerHTML =
  "Welcome " + user.user_first_name + " " + user.user_last_name;

document.getElementById("welcomeMessage").onload = getUserReimbursements();
document.getElementById("submitButton").onclick = insertReimbursement;
document.getElementById("logoutButton").onclick = logout;
document.getElementById("viewYourReimbursementsButton").onclick =
  getUserReimbursements;
document.getElementById("viewAllReimbursementsButton").onclick =
  getAllReimbursements;
document.getElementById("viewPendingReimbursementsButton").onclick = () => {
  getReimbursementsByStatus(1);
};
document.getElementById("viewApprovedReimbursementsButton").onclick = () => {
  getReimbursementsByStatus(2);
};
document.getElementById("viewDeniedReimbursementsButton").onclick = () => {
  getReimbursementsByStatus(3);
};

document.getElementById("updateButton").onclick = updateReimbursementStatus;

function logout() {
  localStorage.removeItem("user");
  window.location.href = "./index.html";
}

async function updateReimbursementStatus() {
  let reimb_id = document.getElementById("reimb_id").value;
  if (reimb_id === "") {
    alert("Please enter a reimbursement ID");
  } else {
    let config = {
      method: "PUT",
      body: document.querySelector('input[name="updateType"]:checked').value,
      credentials: "include",
    };
    let response = await fetch(
      url + "/reimbursements/status/manager/" + reimb_id,
      config
    );
    if (response.status === 202) {
      window.location.reload();
    } else {
      alert("Update Reimbursement Failed");
    }
  }
}

async function getReimbursementsByStatus(status_id) {
  let response = await fetch(url + "/reimbursements/manager/" + status_id);
  document.getElementById("tableBody").innerHTML = "";
  if (status_id === 1) {
    document.getElementById("tableHeader").innerHTML =
      "Pending reimbursements:";
  }
  if (status_id === 2) {
    document.getElementById("tableHeader").innerHTML =
      "Approved reimbursements:";
  }
  if (status_id === 3) {
    document.getElementById("tableHeader").innerHTML = "Denied reimbursements:";
  }
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
    alert("GET REIMBURSEMENTS BY STATUS FAILED");
  }
}

async function getAllReimbursements() {
  let response = await fetch(url + "/reimbursements/manager/all");
  document.getElementById("tableBody").innerHTML = "";
  document.getElementById("tableHeader").innerHTML = "All reimbursements:";

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
    alert("GET ALL REIMBURSEMENTS FAILED");
  }
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
    document.getElementById("tableBody").innerHTML = "";
    document.getElementById("tableHeader").innerHTML = "Your reimbursements:";

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
