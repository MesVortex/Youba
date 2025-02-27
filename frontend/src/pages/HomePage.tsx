import { useEffect, useState } from "react";
import { getCustomers } from "../services/customerService";
import { getAccounts } from "../services/accountService";
import Header from "../components/Header";
import {
    Box,
    Card,
    CardContent,
    Container,
    Typography,
} from "@mui/material";

function HomePage() {
    const [customers, setCustomers] = useState<{ id: number; name: string; email: string }[]>([]);
    const [accounts, setAccounts] = useState<{
        id: number;
        type: string;
        balance: number;
        name: string;
        email: string;
    }[]>([]);

    useEffect(() => {
        async function fetchData() {
            const customersData = await getCustomers();
            const accountsData = await getAccounts();
            setCustomers(customersData);
            setAccounts(accountsData);
        }

        fetchData();
    }, []);

    return (
        <div>
            <Header />
            <Container>
                {/* Customers Section */}
                <Typography variant="h4" gutterBottom sx={{ mt: 12 }}>
                    Customers
                </Typography>
                <Box sx={{ display: "flex", flexWrap: "wrap", gap: 1 }}>
                    {customers.map((c) => (
                        <Card key={c.id} variant="outlined" sx={{ minWidth: 275 }}>
                            <CardContent>
                                <Typography sx={{ color: "text.secondary", fontSize: 14 }} gutterBottom>
                                    Customer Details
                                </Typography>
                                <Typography variant="h5" component="div">
                                    {c.name}
                                </Typography>
                                <Typography sx={{ color: "text.secondary", mb: 1.5 }}>
                                    {c.email}
                                </Typography>
                            </CardContent>
                        </Card>
                    ))}
                </Box>

                {/* Accounts Section */}
                <Typography variant="h4" gutterBottom sx={{ mt: 4 }}>
                    Accounts
                </Typography>
                <Box sx={{ display: "flex", flexWrap: "wrap", gap: 1 }}>
                    {accounts.map((a) => (
                        <Card key={a.id} variant="outlined" sx={{ minWidth: 275 }}>
                            <CardContent>
                                <Typography sx={{ color: "text.secondary", fontSize: 14 }} gutterBottom>
                                    Account Details
                                </Typography>
                                <Typography variant="h5" component="div">
                                    {a.type}
                                </Typography>
                                <Typography sx={{ color: "text.secondary", mb: 1.5 }}>
                                    Balance: ${a.balance}
                                </Typography>
                                <Typography variant="body2">
                                    Owner: {a.name}
                                </Typography>
                            </CardContent>
                        </Card>
                    ))}
                </Box>
            </Container>
        </div>
    );
}

export default HomePage;