const urlLogin = "/user/login";
const urlLogout = "/user/logout";
const urlSignup = "/user/signup";
const urlSession = "/user/current";

let userId = "";
let password = "";

let newUserId = "";
let newPassword = "";
let newUserEmail = "";
let newUserName = "";

document.querySelector("#userId").addEventListener("change", (e) => {
  console.log(e.target.value);
  userId = e.target.value;
});

document.querySelector("#password").addEventListener("change", (e) => {
  console.log(e.target.value);
  password = e.target.value;
});

document.querySelector("#newUserId").addEventListener("change", (e) => {
  console.log(e.target.value);
  newUserId = e.target.value;
});

document.querySelector("#newPassword").addEventListener("change", (e) => {
  console.log(e.target.value);
  newPassword = e.target.value;
});
document.querySelector("#newUserEmail").addEventListener("change", (e) => {
  console.log(e.target.value);
  newUserEmail = e.target.value;
});
document.querySelector("#newUserName").addEventListener("change", (e) => {
  console.log(e.target.value);
  newUserName = e.target.value;
});

document.querySelector(".loginBtn").addEventListener("click", () => {
  const data = {
    userId: userId,
    password: password,
  };

  axios
    .post(urlLogin, data, { withCredentials: true })
    .then((response) => {
      console.log("데이터 : ", response);
      sessionCurrent();
    })
    .catch((error) => {
      console.log("에러 발생 : ", error);
    });
});

document.querySelector(".logoutBtn").addEventListener("click", () => {
  if (confirm("로그아웃 하시겠습니까?")) {
    axios
      .post(urlLogout, {}, { withCredentials: true })
      .then((response) => {
        console.log("데이터 : ", response);
        if (response.status == 200) {
          document.querySelector(".login-box").classList.remove("hidden");
          document.querySelector(".user-box").classList.add("hidden");
        }
      })
      .catch((error) => {
        console.log("에러 발생", error);
      });
  }
});

// 회원가입
document.querySelector(".signupBtn").addEventListener("click", () => {
  document.querySelector(".signup-box").classList.remove("hidden");
  document.querySelector(".login-box").classList.add("hidden");
});

document.querySelector(".signupInputBtn").addEventListener("click", () => {
  const data = {
    userId: newUserId,
    password: newPassword,
    userEmail: newUserEmail,
    userName: newUserName,
  };

  axios
    .post(urlSignup, data, { withCredentials: true })
    .then((response) => {
      if (data.userId != "" && data.password != "") {
        console.log("데이터 : ", response.data);
        document.querySelector(".signup-box").classList.add("hidden");
        document.querySelector(".login-box").classList.remove("hidden");
        alert("회원가입 되었습니다.");
      } else {
        // if (data.userId == "" && data.password == "") {
        document.querySelector(".signup-box").classList.remove("hidden");
        document.querySelector(".login-box").classList.add("hidden");
        alert("아이디와 패스워드는 필수사항 입니다.");
      }
    })
    .catch((error) => {
      console.log("에러 발생 : ", error.response.data);
    });
});

function sessionCurrent() {
  // 로그인 유지 확인 코드
  axios
    .get(urlSession, { withCredentials: true })
    .then((response) => {
      console.log("데이터", response);
      if (response.data.resultCode == "SUCCESS") {
        console.log("세션 유지");
        if (response.status == 200) {
          document.querySelector(".login-box").classList.add("hidden");
          document.querySelector(".user-box").classList.remove("hidden");
          document.querySelector(".user-box p").textContent =
            response.data.data.userId + "님, 환영합니다.";
        }
      }
    })
    .catch((error) => {
      console.log("에러 발생", error.response.data);
    });
}

sessionCurrent();
