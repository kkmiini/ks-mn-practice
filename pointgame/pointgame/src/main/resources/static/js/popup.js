// document.addEventListener("DOMContentLoaded", function() {
	
window.onload = function() {
	       	let results = document.querySelectorAll(".result-content");
            let getpoint = 0;

            let inputs = document.querySelectorAll("input");
            let playButton = document.querySelector(".play-button");

            let modal = document.getElementById("popup-modal");
            let popupMessage = document.getElementById("popup-message");
            let popupClose = document.getElementById("popup-close");
            
            let pointsDisplay = document.getElementById("points");
    let currentPoints = parseInt(pointsDisplay.innerText) || 0;

            results.forEach(function(result) {
                if (result.innerText.trim() === "あたり") {
                    if (results.length <= 5) {
                        getpoint = 1000;
                    } else if (results.length <= 7) {
                        getpoint = 500;
                    } else {
                        getpoint = 200;
                    }
                    showPopup("ゲーム成功！ 取得ポイント : " + getpoint);
                } 

                if (result.innerText.trim() !== "あたり" && results.length === 10) {
                    getpoint = 0;
                    showPopup("ゲーム失敗。 取得ポイント : " + getpoint);
                }
            });

            // 모달 창 띄우기 함수
            function showPopup(message) {
                popupMessage.innerText = message;
                modal.style.display = "flex";
            }

            // 모달 창 닫기 이벤트
            popupClose.addEventListener("click", function() {
                modal.style.display = "none";
                updatePoints(); 
    			disableGame();
            });

            // 게임 비활성화 함수
            function disableGame() {
                inputs.forEach(input => input.disabled = true);
                playButton.disabled = true;
               
	    	
            }
            
             function updatePoints() {
        let newPoints = currentPoints + getpoint;
        pointsDisplay.innerText = newPoints; // 화면 업데이트


    }
  
	        
		         

	    	
	    	
            
        }; 