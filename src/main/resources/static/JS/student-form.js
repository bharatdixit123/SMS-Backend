/**
 * 
 */

function validationForm(){
	function validationForm() {
	    let name = document.getElementById("name").value.trim();
	    let rollNo = document.getElementById("rollno").value.trim();
	    let gender = document.getElementById("gender").value.trim();
	    let age = document.getElementById("age").value.trim();
	    let course = document.getElementById("course").value.trim();
	    let email = document.getElementById("email").value.trim();
	    let phone = document.getElementById("phone").value.trim();

	    // Name
	    if (!/^[A-Za-z ]{1,15}$/.test(name)) {
	        alert("Name must be 1-15 letters only (no @, numbers, special chars)");
	        return false;
	    }

	    // Roll No (optional)
	    if (rollNo !== "" && !/^[0-9]{1,10}$/.test(rollNo)) {
	        alert("Roll No must be 1-10 digits (no letters or special chars)");
	        return false;
	    }

	    // Gender
	    if (!/^(Male|Female|Other)$/.test(gender)) {
	        alert("Please select a valid gender");
	        return false;
	    }

	    // Age (optional)
	    if (age !== "") {
	        age = parseInt(age);
	        if (isNaN(age) || age < 1 || age > 100) {
	            alert("Age must be a number between 1 and 100");
	            return false;
	        }
	    }

	    // Course
	    if (!/^[A-Za-z ]{1,30}$/.test(course)) {
	        alert("Course must be letters only, max 30 characters");
	        return false;
	    }

	    // Email
	    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
	        alert("Enter a valid email address");
	        return false;
	    }
	    

	    // Phone
	    if (!/^[0-9]{10}$/.test(phone)) {
	        alert("Phone number must be 10 digits only");
	        return false;
	    }

	    return true;
	}
}