import { useState } from "react";
import { createAccount } from "../services/accountService";

function AccountsPage() {
    const [balance, setBalance] = useState(0);
    const [type, setType] = useState("CHECKING");
    const [customerId, setCustomerId] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await createAccount({ balance, type, customerId: Number(customerId) });
        alert("Account created successfully!");
    };

    return (
        <div>
            <h1>Create Account</h1>
            <form onSubmit={handleSubmit}>
                <input type="number" placeholder="Balance" value={balance} onChange={(e) => setBalance(Number(e.target.value))} required />
                <select value={type} onChange={(e) => setType(e.target.value)} required>
                    <option value="CHECKING">Checking</option>
                    <option value="SAVINGS">Savings</option>
                </select>
                <input type="number" placeholder="Customer ID" value={customerId} onChange={(e) => setCustomerId(e.target.value)} required />
                <button type="submit">Create Account</button>
            </form>
        </div>
    );
}

export default AccountsPage;
