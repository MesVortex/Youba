import { useState } from "react";
import { addCustomer } from "../services/customerService";

function CustomersPage() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await addCustomer({ name, email });
        alert("Customer added successfully!");
    };

    return (
        <div>
            <h1>Add Customer</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} required />
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <button type="submit">Add Customer</button>
            </form>
        </div>
    );
}

export default CustomersPage;
