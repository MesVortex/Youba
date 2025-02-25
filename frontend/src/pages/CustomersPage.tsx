import { useState } from "react";
import { addCustomer } from "../services/customerService";
import Header from "../components/Header";
import {Alert, Button, Container, Paper, TextField, Typography} from "@mui/material";

function CustomersPage() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await addCustomer({ name, email });
            setSuccess("Customer added successfully");
            setError(null); // Clear any previous errors
        } catch (err: any) {
            setSuccess(null);
            setError(err.response?.data?.detail || "An error occurred");
        }
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
                        {error && <Alert severity="error">{error}</Alert>}
                        {success && <Alert severity="success">{success}</Alert>}
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
