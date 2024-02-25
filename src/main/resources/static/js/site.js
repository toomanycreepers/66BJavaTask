$(document).ready(function () {
    $('#Team').autocomplete({
        source: '/Teams/search'
    });
});

const form = document.querySelector('.footballer-form')
if (form != null)
{
    form.addEventListener('submit', () => {
        const div = document.querySelector('.add-confirmation')
        div.style.display = 'block'
        div.style.animation = 'anime 3s'
        setTimeout(() => {
            div.style.display = 'none'
            div.style.animation = ''
        }, 3000)
    })
}

const wrapper = document.querySelector('.mw')
const openModal = (event) => {
    const country = document.querySelector("#Country")
    desiredText = event.target.parentElement.previousElementSibling.textContent
    for (var i = 0; i < country.options.length; i++) {
      if (country.options[i].text === desiredText) {
        country.options[i].selected = true;
        break;
      }
}
    const team = document.querySelector("#Team")
    team.value = event.target.parentElement.previousElementSibling
        .previousElementSibling.textContent

    const dob = document.querySelector("#Dob")
    dob.value = event.target.parentElement.previousElementSibling
        .previousElementSibling.previousElementSibling.textContent


    const gender = event.target.parentElement.previousElementSibling
        .previousElementSibling.previousElementSibling.previousElementSibling.textContent.trim()
    if (gender === "Мужчина") {
        document.querySelector("#male").checked = true;
    } else if (gender === "Женщина") {
        document.querySelector("#female").checked = true;
    }

    const surname = document.querySelector("#Surname")
    surname.value = event.target.parentElement.previousElementSibling
        .previousElementSibling.previousElementSibling.previousElementSibling
        .previousElementSibling.textContent

    const firstname = document.querySelector("#FirstName")
    firstname.value = event.target.parentElement.previousElementSibling
        .previousElementSibling.previousElementSibling.previousElementSibling
        .previousElementSibling.previousElementSibling.textContent

    const id = document.querySelector("#Id")
    id.value = event.target.getAttribute('data-footballer-id')

    if (!wrapper) return
    wrapper.classList.add('active')
}

let buttons = document.querySelectorAll('.edit-footballer')
if (buttons.length) {
    buttons.forEach(button => {
        button.addEventListener('click', openModal)
    })
}

wrapper.addEventListener('click', (event) => {
    if (event.target !== wrapper) return
    wrapper.classList.remove('active')
})

const applyChangesButton = document.querySelector('.change-footballer')
applyChangesButton.addEventListener('click', () =>{
    if (!applyChangesButton) return
    wrapper.classList.remove('active')
})

var socket = new SockJS('/footballerUpdates');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    stompClient.subscribe('/topic', function(update) {
        $.get("/FootballerList", function(data) {
            $("#footballerTable").replaceWith($(data).find("#footballerTable"));
            $(".edit-footballer").off("click");
            $(".edit-footballer").on("click", openModal);
        });
    });
});