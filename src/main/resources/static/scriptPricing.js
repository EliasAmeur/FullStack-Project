// Clears the console at the beginning of the script execution.
console.clear();

// Selecting HTML elements from the DOM.
const cardsContainer = document.querySelector(".cards");
const cardsContainerInner = document.querySelector(".cards__inner");
const cards = Array.from(document.querySelectorAll(".card")); // Converts NodeList to Array for easy manipulation.
const overlay = document.querySelector(".overlay");

// Function to apply an overlay mask effect.
const applyOverlayMask = (e) => {
  const overlayEl = e.currentTarget;
  // Calculate x and y positions relative to the cards container.
  const x = e.pageX - cardsContainer.offsetLeft;
  const y = e.pageY - cardsContainer.offsetTop;

  // Apply styles to the overlay element with the calculated x, y positions.
  overlayEl.style = `--opacity: 1; --x: ${x}px; --y:${y}px;`;
};

// Function to create a CTA (Call To Action) element on the overlay card.
const createOverlayCta = (overlayCard, ctaEl) => {
  const overlayCta = document.createElement("div");
  overlayCta.classList.add("cta");
  // Sets the text of the CTA from the original card.
  overlayCta.textContent = ctaEl.textContent;
  // Hides the CTA from screen readers for accessibility.
  overlayCta.setAttribute("aria-hidden", true);
  // Appends the CTA to the overlay card.
  overlayCard.append(overlayCta);
};

// ResizeObserver to handle changes in the size of each card.
const observer = new ResizeObserver((entries) => {
  entries.forEach((entry) => {
    // Finding the index of the card being resized.
    const cardIndex = cards.indexOf(entry.target);
    let width = entry.borderBoxSize[0].inlineSize;
    let height = entry.borderBoxSize[0].blockSize;

    // Adjust the corresponding overlay card's size to match.
    if (cardIndex >= 0) {
      overlay.children[cardIndex].style.width = `${width}px`;
      overlay.children[cardIndex].style.height = `${height}px`;
    }
  });
});

// Function to initialize overlay cards corresponding to each card element.
const initOverlayCard = (cardEl) => {
  const overlayCard = document.createElement("div");
  overlayCard.classList.add("card");
  // Create a CTA for the overlay card.
  createOverlayCta(overlayCard, cardEl.lastElementChild);
  // Append the overlay card to the overlay container.
  overlay.append(overlayCard);
  // Start observing the card for size changes.
  observer.observe(cardEl);
};

// Initialize overlay cards for each card in the DOM.
cards.forEach(initOverlayCard);
// Add an event listener to the body to apply the overlay mask effect when the pointer moves.
document.body.addEventListener("pointermove", applyOverlayMask);