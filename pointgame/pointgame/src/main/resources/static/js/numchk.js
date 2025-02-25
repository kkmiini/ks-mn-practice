document.getElementById("play").addEventListener("submit", function(e) {
	const num1 = document.getElementById("num1").value;
	const num2 = document.getElementById("num2").value;
	const num3 = document.getElementById("num3").value;
	
    if (num1 === "" || num2 === "" || num3 === "") {
        e.preventDefault();
        alert("０～9の数字を入力してください");
        return;
    }

	if (num1 == num2 || num1 == num3 || num2 == num3) {
		e.preventDefault();
		alert("重複した数字が入力されました。\n再入力をお願いします。");


	}


});