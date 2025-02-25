import React, { useState } from "react";
import { createAccount } from "../services/accountService";
import Header from "../components/Header";
import {
    Button,
    Container,
    FormControl,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    TextField,
    Typography
} from "@mui/material";

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
            <Header />
            <Container>
                <Typography variant="h4" gutterBottom>
                    Create Account
                </Typography>
                <Paper style={{ padding: "20px" }}>
                    <form onSubmit={handleSubmit}>
                        <TextField
                            label="Balance"
                            type="number"
                            value={balance}
                            onChange={(e) => setBalance(Number(e.target.value))}
                            fullWidth
                            required
                            margin="normal"
                        />
                        <FormControl fullWidth margin="normal">
                            <InputLabel>Type</InputLabel>
                            <Select
                                value={type}
                                onChange={(e) => setType(e.target.value)}
                                required
                            >
                                <MenuItem value="CHECKING">Checking</MenuItem>
                                <MenuItem value="SAVINGS">Savings</MenuItem>
                            </Select>
                        </FormControl>
                        <TextField
                            label="Customer ID"
                            type="number"
                            value={customerId}
                            onChange={(e) => setCustomerId(e.target.value)}
                            fullWidth
                            required
                            margin="normal"
                        />
                        <Button type="submit" variant="contained" color="primary">
                            Create Account
                        </Button>
                    </form>
                </Paper>
            </Container>
        </div>
    );
}

export default AccountsPage;
