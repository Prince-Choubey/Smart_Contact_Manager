console.log("Script loaded");
// Change Theme work
let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme();
});




function changeTheme() {
    // set to web page
    // document.querySelector('html').classList.add(currentTheme);
    changePageTheme(currentTheme, "")


    // set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

   
    // changeThemeButton.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";

    

    changeThemeButton.addEventListener('click', (event) => {
        let oldTheme = currentTheme;
       
        // const oldTheme = currentTheme;
        console.log("Change button Clicked")
        if (currentTheme === "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }

        changePageTheme(currentTheme, oldTheme);

        

    });
}

// Set theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}



// get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
   return theme ? theme : "light";
}

// Change current page theme
function changePageTheme(theme, oldTheme){
    setTheme(currentTheme);

    if(oldTheme){
        document.querySelector("html").classList.remove(oldTheme);
    }
    document.querySelector("html").classList.add(theme);

    // Change the text
    document.querySelector("#theme_change_button").querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}
// End Change theme work