// document.addEventListener("DOMContentLoaded", function() {
	
	

	
	
	
	
document.addEventListener("DOMContentLoaded", function() {
		   let gameOverMessageElement = document.getElementById("gameOverMessage"); 
    let gameOverMessage = gameOverMessageElement ? gameOverMessageElement.innerText.trim() : null;

    if (gameOverMessage && gameOverMessage !== "null") {
        console.log("🛑 게임 종료 메시지 감지:", gameOverMessage);
        popupMessage.innerText = gameOverMessage;
        modal.style.display = "flex";

        // 입력창과 버튼 비활성화
        disableGame();
    }
	
	
	
	
	
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

    let memberId = document.getElementById("memberId").value; // ID 가져오기

    fetch('/updatePoint', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            memberId: memberId,
            newPoints: newPoints
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("서버 오류: " + response.status);
        }
        return response.json();
    })
    .then(data => {
        console.log("서버 응답:", data);
        alert("ポイントが正常に更新されました！");
    })
    .catch(error => {
        console.error("포인트 업데이트 실패:", error);
    });
}
  

	        
		         

	    	
	    	
            
        }); 
        
        
        
        
