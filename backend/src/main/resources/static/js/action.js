function viewCustomer() {
    fetch('http://localhost:8082/api/customers/', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log('Data:', data);
            // Handle the retrieved data from the backend
            displayData(data);
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

function searchByID() {
    let id = document.getElementById("iD").value;

    fetch('http://localhost:8082/api/customers/' + id)
        .then(response => response.json())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the backend
            displayCustomer(data);
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

function insertCustomer() {
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let debt = document.getElementById("debt").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;

    // Create the comma-separated string
    let idString = [name, surname, debt, email, phone].join(',');

    fetch('http://localhost:8082/api/customers/insert?l=' + idString)
        .then(response => response.text())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the backend
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}


function updateCustomer() {
    let customerId = document.getElementById("customerIdInput").value;
    let name = document.getElementById("nameInput").value;
    let surname = document.getElementById("surnameInput").value;
    let debt = document.getElementById("debtInput").value;
    let email = document.getElementById("emailInput").value;
    let phone = document.getElementById("phoneInput").value;

    // Create the comma-separated string
    //understood these are unneccesary but not sure so better stay here
    /*if (name===""){
        name = "???";
    }
    if (surname===""){
        surname = "???";
    }
    if (debt===""){
        debt = "???";
    }
    if (email===""){
        email = "???";
    }
    if (phone===""){
        phone = "???";
    }*/
    let dataString = [name, surname, debt, email, phone].join(',');

    fetch('http://localhost:8082/api/customers/update/'+ customerId +'?l=' + dataString)
        .then(response => response.text())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the backend
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

function deleteById() {
    let id = document.getElementById("idDelete").value;

    fetch('http://localhost:8082/api/customers/delete/' + id)
        .then(response => response.json())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the backend
            displayCustomer(data);
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

function postCustomer() {
    let name = document.getElementById("nameP").value;
    let surname = document.getElementById("surnameP").value;
    let debt = parseFloat(document.getElementById("debtP").value);
    let email = document.getElementById("emailP").value;
    let phone = document.getElementById("phoneP").value;

    let customer = {
        name: name,
        surname: surname,
        debt: debt,
        email: email,
        phone: phone
    };

    fetch('http://localhost:8082/api/customers/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customer)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the server
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

function PutCustomer() {
    // Retrieve values from HTML inputs
    let customerId = document.getElementById("customerIdPut").value;
    let name = document.getElementById("namePU").value;
    let surname = document.getElementById("surnamePU").value;
    let debt = parseFloat(document.getElementById("debtPU").value);
    let email = document.getElementById("emailPU").value;
    let phone = document.getElementById("phonePU").value;

    // Create the updatedCustomer object
    let updatedCustomer = {
        name: name,
        surname: surname,
        debt: debt,
        email: email,
        phone: phone
    };

    fetch(`http://localhost:8082/api/customers/${customerId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedCustomer)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Response:', data);
            // Handle the response from the backend
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle any errors that occur during the request
        });
}

//deleteID
function deleteCustomer(id) {
    fetch(`http://localhost:8082/api/customers/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log('Customer deleted successfully');
            } else {
                console.error('Error deleting customer');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}




function displayData(data) {
    const resultDiv = document.createElement('div');
    resultDiv.innerHTML = '<h2>Customer Information</h2>';

    data.forEach(customer => {
        const customerDiv = document.createElement('div');
        customerDiv.innerHTML = `
      <p>Name: ${customer.name}</p>
      <p>Surname: ${customer.surname}</p>
      <p>Debt: ${customer.debt}</p>
      <p>Email: ${customer.email}</p>
      <p>Phone: ${customer.phone}</p><br>
    `;

        resultDiv.appendChild(customerDiv);
    });

    document.body.appendChild(resultDiv);
}

function displayCustomer(customer) {
    const resultDiv = document.createElement('div');
    resultDiv.innerHTML = '<h2>Customer Information</h2>';


        const customerDiv = document.createElement('div');
        customerDiv.innerHTML = `
      <p>Name: ${customer.name}</p>
      <p>Surname: ${customer.surname}</p>
      <p>Debt: ${customer.debt}</p>
      <p>Email: ${customer.email}</p>
      <p>Phone: ${customer.phone}</p><br>
    `;

        resultDiv.appendChild(customerDiv);


    document.body.appendChild(resultDiv);
}