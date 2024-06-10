document.addEventListener("DOMContentLoaded", function() {
    const ul = document.getElementById("box");
    const passwordBtn = document.getElementById("passwordBtn");
    const password = document.getElementById("password");
    const container = document.querySelector(".container");

    passwordBtn.addEventListener("click", () => {
        if (password.value === "1234") {
            const containerDisplay = window.getComputedStyle(container).getPropertyValue("display");

            if (containerDisplay === "none") {
                container.style.display = "block";
                ul.style.display = "none";
            } else {
                alert("javascript上でのエラー");
            }
        } else {
            alert("パスワードが違います");
        }
    });
});
