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
                <Typography variant="h4" gutterBottom sx={{ mt: 12 }}>
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
                            sx={{
                                "& .MuiOutlinedInput-root": {
                                    "& fieldset": {
                                        borderColor: "#ff3d00", // Border color
                                    },
                                    "&:hover fieldset": {
                                        borderColor: "#ff3d00", // Border color on hover
                                    },
                                    "&.Mui-focused fieldset": {
                                        borderColor: "#ff3d00", // Border color when focused
                                    },
                                },
                                "& .MuiInputLabel-root": {
                                    color: "#ff3d00", // Label color
                                },
                                "& .MuiInputLabel-root.Mui-focused": {
                                    color: "#ff3d00", // Label color when focused
                                },
                            }}
                        />
                        <TextField
                            label="Email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            fullWidth
                            required
                            margin="normal"
                            sx={{
                                "& .MuiOutlinedInput-root": {
                                    "& fieldset": {
                                        borderColor: "#ff3d00", // Border color
                                    },
                                    "&:hover fieldset": {
                                        borderColor: "#ff3d00", // Border color on hover
                                    },
                                    "&.Mui-focused fieldset": {
                                        borderColor: "#ff3d00", // Border color when focused
                                    },
                                },
                                "& .MuiInputLabel-root": {
                                    color: "#ff3d00", // Label color
                                },
                                "& .MuiInputLabel-root.Mui-focused": {
                                    color: "#ff3d00", // Label color when focused
                                },
                            }}
                        />
                        {error && <Alert severity="error">{error}</Alert>}
                        {success && <Alert severity="success">{success}</Alert>}
                        <Button type="submit" variant="outlined" sx={{ color: "#ff3d00", borderColor: "#ff3d00", mt: 2 }}>
                            Add Customer
                        </Button>
                    </form>
                </Paper>
            </Container>
        </div>
    );
}

export default CustomersPage;
