'use strict'

/*스크롤 이벤트*/

const itemContainer = document.querySelector('#item__info')
const itemDetails = document.querySelector('#item__detail');
const itemQna = document.querySelector('#item__qna');
const itemReviews = document.querySelector('#item__reviews');
const itemNavs = document.querySelector('.item__navs')
const itemAside = document.querySelector('.item__aside');

itemNavs.addEventListener('click',event =>{
   if(event.target.className.includes('detail')){
       itemDetails.scrollIntoView({ behavior: 'smooth' });
   }
   else if(event.target.className.includes('qna')){
       itemQna.scrollIntoView({ behavior: 'smooth' });
   }
   else if(event.target.className.includes('review')){
       itemReviews.scrollIntoView({ behavior: 'smooth' });
    }
});

itemAside.addEventListener('click',event => {
    if(event.target.className.includes('info')){
        itemContainer.scrollIntoView({ behavior: 'smooth' });
    }
    else if(event.target.className.includes('detail')){
        itemDetails.scrollIntoView({ behavior: 'smooth' });
    }
    else if(event.target.className.includes('qna')){
        itemQna.scrollIntoView({ behavior: 'smooth' });
    }
    else if(event.target.className.includes('review')){
        itemReviews.scrollIntoView({ behavior: 'smooth' });
    }
})
