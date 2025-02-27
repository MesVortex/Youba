import React, { useState } from "react";
import { createAccount } from "../services/accountService";
import Header from "../components/Header";
import {
    Alert,
    Button,
    Container,
    FormControl,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    TextField,
    Typography,
} from "@mui/material";

function AccountsPage() {
    const [balance, setBalance] = useState(0);
    const [type, setType] = useState("CHECKING");
    const [customerId, setCustomerId] = useState("");
    const [error, setError] = useState<string | null>(null);
    const [success, setSuccess] = useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError(null); // Clear any previous errors
        setSuccess(null); // Clear any previous success messages

        try {
            await createAccount({ balance, type, customerId: Number(customerId) });
            setSuccess("Account created successfully!");
        } catch (err: any) {
            setError(err.detail || err.error || "An error occurred");
        }
    };

    return (
        <div>
            <Header />
            <Container>
                <Typography variant="h4" gutterBottom sx={{ mt: 12 }}>
                    Create Account
                </Typography>
                <Paper style={{padding: "20px"}}>
                    <form onSubmit={handleSubmit}>
                        <TextField
                            label="Balance"
                            type="number"
                            value={balance}
                            onChange={(e) => setBalance(Number(e.target.value))}
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
                        <FormControl fullWidth margin="normal">
                            <InputLabel sx={{color: "#ff3d00"}}>Type</InputLabel>
                            <Select
                                value={type}
                                onChange={(e) => setType(e.target.value)}
                                required
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
                                    "& .MuiSelect-icon": {
                                        color: "#ff3d00", // Dropdown icon color
                                    },
                                }}
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
                        <Button type="submit" variant="outlined" sx={{color: "#ff3d00", borderColor: "#ff3d00", mt: 2}}>
                            Create Account
                        </Button>
                    </form>
                </Paper>
            </Container>
        </div>
    );
}

export default AccountsPage;