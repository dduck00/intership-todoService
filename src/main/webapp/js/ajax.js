function mouseClickEvent(article) {
    const articleInfo = article;
    return () => {
        const moveInformation = 'id=' + article.dataset.id + '&type=' + article.dataset.type;

        request.open("POST", '/action');
        request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        request.send(moveInformation);

        articleInfo.remove();

        if (article.dataset.type === 'TODO') {
            articleInfo.dataset.type = 'DOING';
        } else if (article.dataset.type === 'DOING') {
            articleInfo.dataset.type = 'DONE';
        }
        document.querySelector('#' + articleInfo.dataset.type).innerHTML += articleInfo.outerHTML;

        document.querySelector('#' + articleInfo.id).lastElementChild.addEventListener('click', mouseClickEvent(document.querySelector('#' + articleInfo.id)));

    }
}

const request = new XMLHttpRequest();
request.onreadystatechange = () => {
    if (request.status >= 400) {
        alert("서버 오류 발생");
    }
    
    if(request.readyState === 4 && request.responseText.length !== 0){
        alert(request.responseText);
        location.reload(true);
    }
}


const cards = document.querySelectorAll('.card');
cards.forEach((card) => {
    card.lastElementChild.addEventListener('click', mouseClickEvent(card));
});