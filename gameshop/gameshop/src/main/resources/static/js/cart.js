const url = "http://localhost:8080/api/products/purchaselist";

const data = [
  {
    game: {
      id: 4,
    },
    user: {
      userId: "yami2024",
    },
  },
  {
    game: {
      id: 3,
    },
    user: {
      userId: "yami2024",
    },
  },
];

document.querySelector(".purchaseBtn").addEventListener("click", () => {
  if (confirm("혼또니?")) {
    axios
      .post(url, data, { withCredentials: true })
      .then((response) => {
        console.log("데이터 : ", response.data);
      })
      .catch((error) => {
        console.log("에러 발생 : ", error);
      });
  }
});
