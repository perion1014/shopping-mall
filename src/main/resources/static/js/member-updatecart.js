window.onload = function() {
    // 페이지 로딩이 완료된 후 실행되는 함수
    toggleAllCartCheckbox('cartCheckboxes');
};

//회원 - 장바구니 조회 내 수량 변경 + 변경된 수량 DB에 반영하는 함수
function updateQuantity(changeQuantity, index, cartNo, memberNo, itemNo, itemSize){
    let currentQuantity = document.getElementById('inputvalue_' + index);
    let currentQuantityNum = Number(currentQuantity.value);
    let currentPrice = Number(document.getElementById('itemPrice_' + index).value);
    let priceSumInput = document.getElementById('priceSum');
    let priceSumValue = Number(priceSumInput.innerText);
    let orderSumInput = document.getElementById('orderSum');
    let orderSumValue = Number(orderSumInput.innerText);

    if(changeQuantity === 1){
        if(currentQuantityNum !== 99){
            currentQuantityNum += Number(changeQuantity);
            priceSumValue += Number(changeQuantity) * currentPrice;
            orderSumValue = priceSumValue >= 100000? priceSumValue : priceSumValue + 3000;
             // alert('변경 상품 총액 : ' + priceSumValue);
             // alert('변경 주문 총액 : ' + orderSumValue);
        }
    } else if(changeQuantity === -1){
        if(currentQuantityNum !== 1){
            currentQuantityNum += Number(changeQuantity);
            priceSumValue += Number(changeQuantity) * currentPrice;
            orderSumValue = priceSumValue >= 100000? priceSumValue : priceSumValue + 3000;
        }
    }

    currentQuantity.value = currentQuantityNum;

    // currentQuantityNum += Number(changeQuantity);
    //     if(currentQuantityNum === 0) {currentQuantityNum = 1;}
    //     else if(currentQuantityNum === 100) {currentQuantityNum = 99;}
    // currentQuantity.value = currentQuantityNum;

    fetch('/carts/' + memberNo + '/' + cartNo + '/update',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            currentQuantityNum: currentQuantityNum,
            cartNo: cartNo,
            memberNo: memberNo,
            itemNo: itemNo,
            itemSize: itemSize,
        }),
    }).then(response => response.json())
        .then(data => {
            priceSumInput.innerText = String(priceSumValue);
            orderSumInput.innerText = String(orderSumValue);
        }).catch(error => {
            alert('전송 실패 - Error : ' + error);
    })

}

//회원 - 장바구니 조회 페이지 내, 장바구니 항목 삭제 함수
function deleteCart(index, cartNo, memberNo){
    let deleteCheck = confirm('장바구니에서 해당 항목을 삭제하시겠습니까?');
    if(deleteCheck == true){
        fetch('/carts/' + memberNo + '/' + cartNo + '/delete',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify({
                cartNo: cartNo,
                memberNo: memberNo,
            }),
        }).then(response => response.text())
            .then(date => {
                alert("삭제되었습니다.");
                location.reload();
            }).catch(error =>{
                alert("삭제에 실패하였습니다 - 사유 : " + error);
        })
    } else{
        alert("취소하셨습니다.");
    }
}

//전체 선택 토글 시 체크or해제 + 가격 재갱신 함수
function toggleAllCartCheckbox(className){

    let checkBoxes = document.querySelectorAll('.' + className);
    let thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
            for(var i = 0; i < checkBoxes.length; i++){
                checkBoxes[i].checked = thisBoxChecked;
            }

    calculatePriceSum(checkBoxes.length);

}


//체크박스 선택 시 상품 총액 및 주문 총액 변경
function calculatePriceSum(cartDTOListSize){

    //alert('자바스크립트로 넘긴 리스트 인덱스 체크 : ' + Number(cartDTOListSize));
    let itemPrice = 0;
    let itemQuantity = 0;
    let priceSum = 0;
    let orderSum = 0;

    for(let i = 0; i < cartDTOListSize; i++){

        if(document.getElementById('cartCheckBox_'+ i).checked === true){

        itemPrice = document.getElementById('itemPrice_' + i).value;
        itemQuantity = document.getElementById('inputvalue_' + i).value;

        priceSum += itemPrice * itemQuantity;
        orderSum = priceSum >= 100000? priceSum : priceSum + 3000;
        }

        //alert('for문돌려서 확인하는 상품 가격 : ' + itemPrice);
        //alert('상품 수량 : ' + itemQuantity);
    }

        document.getElementById('priceSum').innerText = priceSum;
        document.getElementById('orderSum').innerText = orderSum;
}

//장바구니 상품 선택하고 구매 누를 시, 구매 페이지로 넘어가기 전 재고 체크 함수
function checkItemStock(cartDTOListSize, memberNo){

    let jsonData= [];

    let itemName = '';
    let itemSize = '';
    let itemQuantity = 0;
    let itemPrice = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        if(document.getElementById('cartCheckBox_' + i).checked === true){
            itemName = document.getElementById('itemName_' + i).innerText;
            itemSize = document.getElementById('itemSize_' + i).innerText;
            itemQuantity = document.getElementById('inputvalue_' + i).value;
            itemPrice = document.getElementById('itemPrice_' + i).value;
            // alert('자스에서 받아온 아이템 명 : ' + itemName);
            // alert('자스에서 받아온 아이템 사이즈 : ' + itemSize);
            // alert('자스에서 받아온 아이템 수량 : ' + selectedItemQuantity);
            let jsonItem = {itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};
            jsonData.push(jsonItem);
            // alert('아이템 명 : ' + itemName);
            // alert('아이템 사이즈 : ' + itemSize);
        }
    }

    // for(var i = 0; i < jsonData.length; i++){
    //     alert('JSON담은 이름 : ' + jsonData[i].itemName);
    //     alert('JSON담은 사이즈 : ' + jsonData[i].itemSize);
    // }

    fetch('/members/' + memberNo + '/orders/check-itemstock', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            alert(JSON.stringify(data.response));

        }).catch(error => {
            alert('JSON 전송 실패 - 사유 : ' + error);
    })
}


//장바구니 상품 선택하고 구매 누를 시, 구매 페이지로 넘어가기 전 재고 체크 함수
function checkItemStock2(cartDTOListSize, memberNo){

    let jsonData= [];

    let itemNo = 0;
    let itemName = '';
    let itemSize = '';
    let itemQuantity = 0;
    let itemPrice = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        // alert(`i: ${i}`);
        if(document.getElementById(`cartCheckBox_${i}`).checked === true){
            itemNo = document.getElementById(`itemNo_${i}`).value;
            itemName = document.getElementById(`itemName_${i}`).innerText;
            itemSize = document.getElementById(`itemSize_${i}`).innerText;
            itemQuantity = document.getElementById(`inputvalue_${i}`).value;
            itemPrice = document.getElementById(`itemPrice_${i}`).value;

            let jsonItem = {itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};
            jsonData.push(jsonItem);
        }
    }

    fetch(`/members/${memberNo}/orders/check-itemstock`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            if(JSON.stringify(data.response).replaceAll('"', '') === '선택하신 상품의 재고가 없습니다.'){
                alert(JSON.stringify(data.response));
                location.reload();
            } else {
                fetch(`/members/${memberNo}/orders/create`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(jsonData)
                }).then( data => {
                    location.href = '/members/orders/create';
                })
            }

        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    })
}

function checkItemStockPurchase(memberNo, itemNo, itemName, itemPrice){
    // alert('온클릭 진입');

    let jsonData= [];

    let itemSize = '';
    let itemQuantity = 0;

    let itemSizelist = document.getElementsByName(`itemSize`);
    let priceSum = Number(document.getElementById('priceSum').innerText);
    let orderSum = Number(document.getElementById('orderSum').innerText);


    for (var i=0; i <itemSizelist.length; i++) {
        if (itemSizelist.item(i) !== null) {
            itemSize = itemSizelist.item(i).value;
        }
    }

    itemQuantity = document.getElementById(`item__count`).value;

    let jsonItem = {priceSum: priceSum, orderSum:orderSum, memberNo: memberNo, itemNo: itemNo, itemName: itemName, itemSize: itemSize, itemQuantity: itemQuantity, itemPrice: itemPrice};

    jsonData.push(jsonItem);


    // fetch(`/carts/${memberNo}`) {}

    fetch(`/members/${memberNo}/orders/check-itemstock`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {

            if(JSON.stringify(data.response).replaceAll('"', '') === '선택하신 상품의 재고가 없습니다.'){
                alert(JSON.stringify(data.response));
                location.reload();
            } else {
                fetch(`/members/${memberNo}/orders/create`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(jsonData)
                }).then( data => {
                    location.href = '/members/orders/create';
                })
            }

        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    })
}


