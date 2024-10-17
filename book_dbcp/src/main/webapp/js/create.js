// 추가 클릭 시 form submit 일어나면
// description 요소를 제외한 모든 요소에 값이 있는지 확인
// code 는 4자리이고 숫자여야 함
// price 는 3 ~ 10 자리 숫자여야 함

	document.querySelector("body div:nth-child(3) form").addEventListener("submit", (e)=>{
	
	// submit 중지
	e.preventDefault();
	
	const form = e.target;
	
	// form 내부 요소 지정
	const code = form.querySelector("#code");
	const title = form.querySelector("#title");
	const writer = form.querySelector("#writer");
	const price = form.querySelector("#price");
	
	// price 는 3 ~ 10 자리 숫자여야 함	
	const codeReg = /^[0-9]{4}$/;
	// 최소 한자리 : +
	const textReg = /[A-Za-z가-힇0-9]+/;
	const priceReg = /^[0-9]{3,10}$/;
			
		if (!codeReg.test(code.value)) {
			alert("도서코드는 4자리 숫자로 입력해야 합니다.");
			code.select(); // focus + 입력값 존재 시 블럭으로 잡아줌
			return;
		} else if (!textReg.test(title.value)) {
			alert("도서명을 입력하세요.");
			title.select(); 
			return;
		} else if (!textReg.test(writer.value)) {
			alert("저자를 입력하세요.");
			writer.select(); 
			return;
		} else if (!priceReg.test(price.value)) {
			alert("가격은 숫자로 입력해야 합니다.");
			price.select(); 
			return;
		}	
	// 이상이 없는 경우 form 전송
	form.submit();
});