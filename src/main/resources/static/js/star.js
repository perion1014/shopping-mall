const starSize = 30, maxStar = 5, gutter = 2;//별 크기, 별 개수
let maskMax = 0; //오버레이 마스크 최대 너비
window.addEventListener('DOMContentLoaded',()=>{
    //별 이미지 SVG 개수만큼 생성
    for(let i = 0;i < maxStar;i++){
        let el = document.createElement('div');
        //el.classList.add('star');
        el.style.width = starSize + 'px';
        el.style.height = starSize + 'px';
        el.style.marginRight = gutter + 'px';
        //인라인 SVG 이미지 부착
        el.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"><path fill="none" class="starcolor" d="M381.2 150.3L524.9 171.5C536.8 173.2 546.8 181.6 550.6 193.1C554.4 204.7 551.3 217.3 542.7 225.9L438.5 328.1L463.1 474.7C465.1 486.7 460.2 498.9 450.2 506C440.3 513.1 427.2 514 416.5 508.3L288.1 439.8L159.8 508.3C149 514 135.9 513.1 126 506C116.1 498.9 111.1 486.7 113.2 474.7L137.8 328.1L33.58 225.9C24.97 217.3 21.91 204.7 25.69 193.1C29.46 181.6 39.43 173.2 51.42 171.5L195 150.3L259.4 17.97C264.7 6.954 275.9-.0391 288.1-.0391C300.4-.0391 311.6 6.954 316.9 17.97L381.2 150.3z"/></svg>';
        document.querySelector('.rating').appendChild(el);
    }

    maskMax = parseInt(starSize * maxStar + gutter * (maxStar-1));//최대 마스크 너비 계산
    document.querySelector('input[name=ratevalue]').max = maxStar;//입력 필드 최대값 재설정
    setRating(document.querySelector('input[name=ratevalue]').value);//초기 별점 마킹

    //별점 숫자 입력 값 변경 이벤트 리스너
    document.querySelector('input[name=ratevalue]').addEventListener('change',(e)=>{
        const val = e.target.value;
        //계산식 - 마스크 최대 너비에서 별점x별크기를 빼고, 추가로 별점 버림 정수값x거터 크기를 빼서 마스크 너비를 맞춤
        setRating(val);
    })

    //별점 마킹 함수
    function setRating(val = 0){
        document.querySelector('.overlay').style.width = parseInt(maskMax - val * starSize - Math.floor(val) * gutter) + 'px';//마스크 크기 변경해서 별점 마킹
    }

})

// https://apost.dev/1142/ 에서 가져옴