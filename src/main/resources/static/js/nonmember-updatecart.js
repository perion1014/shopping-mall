//alert('nonmember-updatecart.js 적용 확인');

//비회원 장바구니 조회 내 수량 변경 함수
function updateQuantity(changeQuantity, cartListIndex){
    let currentQuantityInput = document.getElementById('inputvalue_' + cartListIndex);
    let currentQuantityValue = Number(currentQuantityInput.value);
    //alert(currentQuantityValue);
    if(changeQuantity == 1){
        if(currentQuantityValue != 99){
            currentQuantityValue += Number(changeQuantity);
        }
    } else if(changeQuantity == -1){
        if(currentQuantityValue != 1){
            currentQuantityValue += Number(changeQuantity);
        }
    }

    currentQuantityInput.value = currentQuantityValue;

    fetch('/carts/' + cartListIndex + '/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            changeQuantity: changeQuantity,
            cartListIndex: cartListIndex,
        }),
    }).then(response => response.text())
        .then(data => {
            //alert('JSON JAVA로 전송 성공');
        }).catch(error => {
            alert('JSON 전송 실패 - 사유 : ' + error);
    })

}

function sendCartDeleteJson(cartNo){

    fetch('/carts/' + (cartNo+1) + '/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            cartNo: cartNo,
        }),

    }).then(response => response.text())
        .then(data => {
            alert('삭제되었습니다.');
            location.reload();
        }).catch(error => {
            alert('JSON 전송 실패 : 사유 - ' + error);
    })
}

//비회원 - 모든 장바구니 항목을 선택/해제하는 체크박스
function toggleAllCartCheckbox(checkBoxClass){

    var checkBoxes = document.querySelectorAll('.' + checkBoxClass);
    thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
    for(var i = 0; i < checkBoxes.length; i++){
        checkBoxes[i].checked = thisBoxChecked;
    }

}

function toggleAllCartCheckbox(className){

    var checkBoxes = document.querySelectorAll('.' + className);
    thisBoxChecked = Boolean(document.getElementById('cartListAllCheckBtn').checked);
    for(var i = 0; i < checkBoxes.length; i++){
        checkBoxes[i].checked = thisBoxChecked;
    }

    calculatePriceSum(checkBoxes.length);

}

function calculatePriceSum(cartDTOListSize){

    //alert('자바스크립트로 넘긴 리스트 인덱스 n 체크 : ' + Number(cartDTOListSize));
    var itemPrice = 0;
    var itemQuantity = 0;
    var priceSum = 0;
    var orderSum = 0;

    for(var i = 0; i < cartDTOListSize; i++){

        if(document.getElementById('cartCheckBox_'+ i).checked === true){
            itemPrice = document.getElementById('itemPrice_' + i).value;
            itemQuantity = document.getElementById('inputvalue_' + i).value;

            priceSum += itemPrice * itemQuantity;
            orderSum = priceSum - 1000;
        }

    }

    document.getElementById('priceSum').innerText = priceSum;
    document.getElementById('orderSum').innerText = orderSum;
}


//비회원 - 장바구니 목록에서 선택한 물건들 구매 눌렀을 때 재고 체크
function checkItemStock(cartDTOListSize){

    let jsonData= [];

    let itemNo = 0;
    let itemName = '';
    let itemSize = '';
    let itemQuantity = 0;
    let itemPrice = 0;

    for(var i = 0; i < cartDTOListSize; i++){
        if(document.getElementById('cartCheckBox_' + i).checked === true){
            itemNo = document.getElementById('itemNo_' + i).value;
             // alert('itemNo : ' + itemNo);
            itemName = document.getElementById('itemName_' + i).innerText;
             // alert('itemName : ' + itemName);
            itemSize = document.getElementById('itemSize_' + i).innerText;
             // alert('itemSize : ' + itemSize);
            itemQuantity = document.getElementById('inputvalue_' + i).value;
             // alert('itemQuantity : ' + itemQuantity);
            itemPrice = document.getElementById('itemPrice_' + i).value;
             // alert('itemPrice :' + itemPrice);
            var jsonItem = {itemNo:itemNo, itemName: itemName, itemSize: itemSize, itemQuantity : itemQuantity, itemPrice: itemPrice};
            jsonData.push(jsonItem);
        }
    }

    fetch('/orders/check-itemstock', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {
            //alert(JSON.stringify(data.response));
                if(JSON.stringify(data.response).replaceAll('"', '') !== '데이터 전달/처리 성공'){
                var stocksArray = JSON.stringify(data.response).split(',');
                var noStockMessage = '';

                for(var i = 0; i < stocksArray.length; i++){
                noStockMessage += stocksArray[i] + '\n';
                }

                alert(noStockMessage.replaceAll('"', '') + '상품의 재고가 부족합니다.');
            } else{
              location.href = '/orders/create';
            }
        }).catch(error => {
        alert('JSON 전송 실패 - 사유 : ' + error);
    })
}