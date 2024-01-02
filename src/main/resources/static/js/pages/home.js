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
    location.href='/items/all';
})


/*Best 상품 섹션 캐러셀 이벤트*/

const slidePrev1 = document.querySelector('.slide__prev1');
const slidePrev2 = document.querySelector('.slide__prev2');
const slidePrev3 = document.querySelector('.slide__prev3');
const slideNext1 = document.querySelector('.slide__next1');
const slideNext2 = document.querySelector('.slide__next2');
const slideNext3 = document.querySelector('.slide__next3');
const slideContainer = document.querySelector('.slide__container');

slidePrev1.addEventListener('click', () => {
    slideContainer.style.transform='translateX(-66.666666%)';
});

slideNext1.addEventListener('click', () =>{
    slideContainer.style.transform = 'translateX(-33.333333%)';
});

slidePrev2.addEventListener('click', () => {
    slideContainer.style.transform='translateX(0%)';
});

slideNext2.addEventListener('click', () =>{
    slideContainer.style.transform = 'translateX(-66.666666%)';
});
slidePrev3.addEventListener('click', () => {
    slideContainer.style.transform='translateX(-33.333333%)';
});
slideNext3.addEventListener('click', () =>{
    slideContainer.style.transform = 'translateX(0%)';
});

