let currentSectionIndex = 0; // 0 for welcome, 1 for section1, 2 for section2
const sections = ['welcome', 'section1', 'section3','section2'];
document.getElementById('welcome').classList.add('active');

let isMenuClicked = false;

//event handler for interactive scrolling
window.addEventListener('wheel', function(event) {
    if(isMenuClicked) return;

    let delta = Math.sign(event.deltaY);
    
    // If we are on section3 (the slider)
    if (sections[currentSectionIndex] === 'section3') {
        const slider = document.getElementById('section3').querySelector('[x-ref="slider"]');
        
        // If scrolling down and haven't reached the end of the slider
        if (delta > 0 && Math.abs((slider.scrollWidth - slider.offsetWidth) - slider.scrollLeft) > 5) {
            slider.scrollBy({left: slider.offsetWidth, behavior: 'smooth'});
            return event.preventDefault(); // prevent default to stop vertical scroll
        }

        // If scrolling up and haven't reached the start of the slider
        if (delta < 0 && slider.scrollLeft > 5) {
            slider.scrollBy({left: slider.offsetWidth * -1, behavior: 'smooth'});
            return event.preventDefault(); // prevent default to stop vertical scroll
        }
    }

    if (delta > 0 && currentSectionIndex < sections.length - 1) {
        // Scrolling down and not on the last section
        document.getElementById(sections[currentSectionIndex]).classList.remove('active'); 
        currentSectionIndex++;
        document.getElementById(sections[currentSectionIndex]).classList.add('active');
    } else if (delta < 0 && currentSectionIndex > 0) {
        // Scrolling up and not on the first section
        document.getElementById(sections[currentSectionIndex]).classList.remove('active');
        currentSectionIndex--;
        document.getElementById(sections[currentSectionIndex]).classList.add('active');
    }

    event.preventDefault();
}, { passive: false });

//event handler for menu clicking
let links = document.querySelectorAll(".menu a");

links.forEach(function(link) {
    link.addEventListener("click", function(event) {
        
        isMenuClicked = true;

        let id = this.getAttribute("href").slice(1); // remove the '#' prefix

        if (this.getAttribute("href").startsWith("#")) {
            //\event.preventDefault();

            // Find the clicked section's index
            const clickedSectionIndex = sections.indexOf(id);
            
            if (clickedSectionIndex !== -1) {
                // Remove 'active' class from the current section
                document.getElementById(sections[currentSectionIndex]).classList.remove('active');

                // Update the current section index
                currentSectionIndex = clickedSectionIndex;
                
                // Add 'active' class to the clicked section
                document.getElementById(sections[currentSectionIndex]).classList.add('active');
                console.log('Link clicked for section: '  +id);

                // Scroll to the clicked section
                document.getElementById(sections[currentSectionIndex]).scrollIntoView({ behavior: "smooth", block: "start" });
               
            }
        }
        setTimeout(function(){
            isMenuClicked=false;
        },2000);
    });
});




