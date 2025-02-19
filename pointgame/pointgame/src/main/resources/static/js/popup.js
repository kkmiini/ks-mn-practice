    document.addEventListener("DOMContentLoaded", function() {
        // 모든 결과 값을 가져옴
        let results = document.querySelectorAll(".result-content");
        let getpoint = 0;


        // 결과 중 "あたり"가 있는지 확인
        results.forEach(function(result) {
            if (result.innerText.trim() === "あたり") {
				
				if(results.length <= 5) {
					getpoint = 1000;
				
					} else if(results.length <= 7) {
						getpoint = 500;
						
						} else {
							getpoint = 200;
							
						}
				alert("ゲーム成功!"  + "取得ポイント" + getpoint );
            } 	
            
            
            if (result.innerText.trim() !== "あたり" && results.length === 10) {
				getpoint = 0;
        		alert("ゲーム失敗。" + "取得ポイント" + getpoint) ;
    		}
        });
    });
    
    
   