const url = "http://localhost:8080/api/products/purchaselist";

function sessionCurrent() {
  axios
    .get("http://localhost:8080/user/current", { withCredentials: true })
    .then((response) => {
      console.log("데이터 : ", response.data);
      if (response.status == 200) {
        const userId = response.data.userId;
        const authority = response.data.authority[0].authority;
        let cartItems = JSON.parse(localStorage.getItem(userId));
        if (cartItems) {
          displayCart(cartItems, userId);
          const data = cartItems.map((game) => {
            // purchase객체를 만들어서 리턴
            return {
              game: game,
              user: { userId: userId, authority: { authorityName: authority } },
            };
          });
          document
            .querySelector(".purchaseBtn")
            .addEventListener("click", () => {
              if (confirm("환불안댐!")) {
                axios
                  .post(url, data, { withCredentials: true })
                  .then((response) => {
                    console.log("데이터 : ", response.data);
                    localStorage.removeItem(userId);
                    window.location.reload();
                  })
                  .catch((error) => {
                    console.log("에러 발생 : ", error);
                  });
              }
            });
        }
      }
    })
    .catch((error) => {
      console.log("에러 발생 : ", error);
      alert("로그인 해주세요.");
    });
}

function displayCart(games, userId) {
  const tbody = document.querySelector(".cart-body");
  let totalPrice = 0;

  games.forEach((data, index) => {
    // 태그 요소 생성
    const tr = document.createElement("tr");
    const deltd = document.createElement("td");
    const imgtd = document.createElement("td");
    const title = document.createElement("td");
    const genre = document.createElement("td");
    const price = document.createElement("td");
    const img = document.createElement("img");
    const deleteBtn = document.createElement("button");
    // 클래스 이름 생성
    imgtd.classList.add("imgtd");
    img.classList.add("image");
    deleteBtn.classList.add("deleteBtn");
    // 태그 속성 추가
    img.src = data.image;
    title.textContent = data.title;
    genre.textContent = data.genre;
    price.textContent = data.price + "원";
    deleteBtn.textContent = "삭제";
    // appendChild 부모,자식 위치 설정
    imgtd.appendChild(img);
    deltd.appendChild(deleteBtn);
    tr.appendChild(imgtd);
    tr.appendChild(title);
    tr.appendChild(genre);
    tr.appendChild(price);
    tr.appendChild(deltd);
    tbody.appendChild(tr);

    totalPrice = totalPrice + data.price;
  });
  document.querySelector(".totalPrice").textContent = "총 " + totalPrice + "원";
  document.querySelector(".deleteAllBtn").addEventListener("click", () => {
    if (confirm("다 지운당?")) {
      localStorage.clear();
      window.location.reload();
    }
  });

  // 06.03 선생님 솔루션 (axios 삭제 및 ...deletedData 수정)

  const deleteBtns = document.querySelectorAll(".deleteBtn");
  console.log(deleteBtns);
  deleteBtns.forEach((deleteBtn, index) => {
    deleteBtn.addEventListener("click", () => {
      if (confirm("장바구니에서 삭제하시겠습니까?")) {
        const deletedData = games.toSpliced(index, 1);
        console.log("deletedData :", deletedData);
        const deletedArr = JSON.stringify(deletedData);
        console.log("deletedArr :", deletedArr);
        localStorage.setItem(userId, deletedArr);
        window.location.reload();
        // 06.02 삭제버튼 만들기 구글링 후 성공
        // 삭제버튼에게 각각 인덱스값 부여
        // toSpliced를 적용, 인덱스의 1번값 삭제 (첫번째 value임이 고정이기 때문)
        // deletedArr 의 [...deletedData] 처럼 ...을 사용하여 잘라내는것이 신기했음.
        // setItem으로 응답받은 userId (key)와 삭제할 배열을 할당받은 deletedArr로 localStorage수정
        // log로 삭제 확인후 페이지 새로고침!
      }
    });
  });
}

// 페이지 로딩시에 즉시 세션여부 확인
sessionCurrent();

// 06.02 성공
//   axios
//     .get("http://localhost:8080/user/current", { withCredentials: true })
//     .then((response) => {
//       console.log("데이터 : ", response.data);
//       if (response.status == 200) {
//         const deleteBtns = document.querySelectorAll(".deleteBtn");
//         console.log(deleteBtns);
//         deleteBtns.forEach((deleteBtn, index) => {
//           deleteBtn.addEventListener("click", () => {
//             if (confirm("장바구니에서 삭제하시겠습니까?")) {
//               const deletedData = games.toSpliced(index, 1);
//               console.log("deletedData :", deletedData);
//               const deletedArr = JSON.stringify([...deletedData]);
//               console.log("deletedArr :", deletedArr);
//               localStorage.setItem(response.data.userId, deletedArr);
//               // window.location.reload();
//               // 06.02 삭제버튼 만들기 구글링 후 성공
//               // 삭제버튼에게 각각 인덱스값 부여
//               // toSpliced를 적용, 인덱스의 1번값 삭제 (첫번째 value임이 고정이기 때문)
//               // deletedArr 의 [...deletedData] 처럼 ...을 사용하여 잘라내는것이 신기했음.
//               // setItem으로 응답받은 userId (key)와 삭제할 배열을 할당받은 deletedArr로 localStorage수정
//               // log로 삭제 확인후 페이지 새로고침!
//             }
//           });
//         });
//       }
//     })
//     .catch((error) => {
//       console.log("에러 발생 : ", error);
//       alert("로그인 해주세요.");
//     });
// }

// 06.01 function 으로 외부 호출 시도
// function delCartItem(index) {
//   axios
//     .get("http://localhost:8080/user/current", { withCredentials: true })
//     .then((response) => {
//       console.log("데이터 : ", response.data);
//       if (response.status == 200) {
//         const userId = response.data.userId;
//         let cartItem = JSON.parse(localStorage.getItem(userId));
//         if (!cartItem) {
//           cartItem = [];
//         }

//         const deleteBtns = document.querySelectorAll(".deleteBtn");
//         console.log(deleteBtns);
//         deleteBtns.forEach((deleteBtn, index) => {
//           deleteBtn.addEventListener("click", () => {
//             const deletedData = data.toSpliced(index, 1);
//             const deletedArr = JSON.stringify([...deletedData]);
//             localStorage.setItem(userId, deletedArr); //
//             window.location.reload();
//           });
//         });

// 06.02 장바구니 담기 버튼과 유사한 삭제버튼 만들기 시도
//         // // localStorage.getItem(userId);
//         // // JSON.parse(localStorage.getItem('key이름'))
//         // JSON.parse(localStorage.getItem(userId)).push(넣을 데이터);
//         // JSON.stringify(JSON.parse(localStorage.getItem('key이름')))
//         // localStorage.setItem('key이름',JSON.stringify(JSON.parse(localStorage.getItem('key이름'))))
//       }
//     })
//     .catch((error) => {
//       console.log("삭제 에러 발생 : ", error);
//       alert("로그인 해주세요.");
//     });
// }
