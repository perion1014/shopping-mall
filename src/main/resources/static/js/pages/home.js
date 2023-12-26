/*배너 섹션 버튼 이벤트 */

const memberAddBtn = document.querySelector('.btn--member-add');

memberAddBtn.addEventListener('click',()=>{
    location.href='/members/add';
});

const noticeBtn = document.querySelector('.btn--notice');
noticeBtn.addEventListener('click',()=>{
    location.href='/notice';
})

const itemsBtn = document.querySelector('.btn--items');
itemsBtn.addEventListener('click',()=>{
    location.href='/items';
})


/*Best 상품 섹션 캐러셀 이벤트*/



const slidePrev = document.querySelector('.slide__prev');
const slideNext = document.querySelector('.slide__next');
const slideContainer = document.querySelector('.slide__container');

let slideNum = 0;

function slideMove(){
    slideContainer.style.transform = `translateX(-${33.333333 * slideNum}%)`;
}

slidePrev.addEventListener('click', () => {
    //slideContainer.style.transform = `translateX(-${33.333333 * slideNum}%)`;
    slideContainer.style.transform='translateX(0)'
});

slideNext.addEventListener('click', () =>{
    //slideContainer.style.transform = `translateX(-${33.333333 * slideNum}%)`;

    if(slideNum == 2){
        slideNum = 0;
    }
    else{
        slideNum++;
    }
    slideContainer.style.transform = `translateX(-${33.333333 * slideNum}%)`;
});

