document.getElementById('addExerciseButton').addEventListener('click', function() {
    var container = document.getElementById('exercisesContainer');
    var exerciseCount = container.children.length;

    var newExerciseDiv = document.createElement('div');
    newExerciseDiv.className = 'exercise-entry';
    newExerciseDiv.innerHTML = `
        <table class="exercise-table">
            <tr>
                <td><label for="exerciseName${exerciseCount}">Exercise Name:</label></td>
                <td><input type="text" id="exerciseName${exerciseCount}" name="exercises[${exerciseCount}].name" placeholder="Exercise Name" required></td>
            </tr>
            <tr>
                <td><label for="imageFile${exerciseCount}">Image:</label></td>
                <td><input type="file" id="imageFile${exerciseCount}" name="exercises[${exerciseCount}].imageFile" accept="image/*"></td>
            </tr>
            <tr>
                <td><label for="sets${exerciseCount}">Sets:</label></td>
                <td><input type="number" id="sets${exerciseCount}" name="exercises[${exerciseCount}].sets" placeholder="Sets" required></td>
            </tr>
            <tr>
                <td><label for="reps${exerciseCount}">Reps:</label></td>
                <td><input type="number" id="reps${exerciseCount}" name="exercises[${exerciseCount}].reps" placeholder="Reps" required></td>
            </tr>
            <tr>
                <td><label for="duration${exerciseCount}">Rest:</label></td>
                <td><input type="number" id="duration${exerciseCount}" name="exercises[${exerciseCount}].duration" placeholder="Duration" required></td>
            </tr>
            <tr>
                <td><label for="notes${exerciseCount}">Notes:</label></td>
                <td><textarea id="notes${exerciseCount}" name="exercises[${exerciseCount}].notes" placeholder="Notes"></textarea></td>
            </tr>
        </table>
        <hr>
    `;
    container.appendChild(newExerciseDiv);
});