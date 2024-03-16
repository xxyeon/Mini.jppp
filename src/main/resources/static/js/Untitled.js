const daysTag = document.querySelector(".days"),
  currentDate = document.querySelector(".current-date"),
  prevNextIcon = document.querySelectorAll(".icons span");

let date = new Date(),
  currYear = date.getFullYear(),
  currMonth = date.getMonth();

const months = ["January", "February", "March", "April", "May", "June", "July",
  "August", "September", "October", "November", "December"];

const renderCalendar = () => {
  let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(),
    lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(),
    lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(),
    lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();
  let liTag = "";

  for (let i = firstDayofMonth; i > 0; i--) {
    liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
  }

  for (let i = 1; i <= lastDateofMonth; i++) {
    let isToday = i === date.getDate() && currMonth === new Date().getMonth()
      && currYear === new Date().getFullYear() ? "active" : "";
    let isPastDate = new Date(currYear, currMonth, i) < new Date().setHours(0,0,0,0) ? "past-date" : "";
    let isFutureDate = new Date(currYear, currMonth, i) > new Date().setHours(0,0,0,0) ? "future-date" : "";
    liTag += `<li class="${isToday} ${isPastDate} ${isFutureDate}" onclick="handleDateClick(${i})">${i}</li>`;
  }

  for (let i = lastDayofMonth; i < 6; i++) {
    liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`
  }
  currentDate.innerText = `${months[currMonth]} ${currYear}`;
  daysTag.innerHTML = liTag;
}
renderCalendar();

prevNextIcon.forEach(icon => { 
  icon.addEventListener("click", () => { 
    currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;
    if (currMonth < 0 || currMonth > 11) { 
      date = new Date(currYear, currMonth, new Date().getDate());
      currYear = date.getFullYear(); 
      currMonth = date.getMonth(); 
    } else {
      date = new Date(); 
    }
    renderCalendar(); 
  });
});

function handleDateClick(day) {
    let clickedDate = new Date(currYear, currMonth, day);
    let today = new Date();
    
  if (new Date(currYear, currMonth, day) < new Date().setHours(0,0,0,0)) {
    alert(`출석체크는 당일만 가능합니다.`);
  } else if (new Date(currYear, currMonth, day) > new Date().setHours(0,0,0,0)) {
    alert(`출석체크는 당일만 가능합니다.`);
  } else {
    alert(`출석체크 완료!`); /*${months[currMonth]} ${day}, ${currYear} 추가할지 고민중*/
  }
}
