import { useState } from "react";
import { addCustomer } from "../services/customerService";
import Header from "../components/Header";
import {Button, Container, Paper, TextField, Typography} from "@mui/material";

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
            <Header />
            <Container>
                <Typography variant="h4" gutterBottom>
                    Add Customer
                </Typography>
                <Paper style={{ padding: "20px" }}>
                    <form onSubmit={handleSubmit}>
                        <TextField
                            label="Name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            fullWidth
                            required
                            margin="normal"
                        />
                        <TextField
                            label="Email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            fullWidth
                            required
                            margin="normal"
                        />
                        <Button type="submit" variant="contained" color="primary">
                            Add Customer
                        </Button>
                    </form>
                </Paper>
            </Container>
        </div>
    );
}

export default CustomersPage;
