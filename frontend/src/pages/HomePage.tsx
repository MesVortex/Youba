import {useEffect, useState} from "react";
import {getCustomers} from "../services/customerService";
import {getAccounts} from "../services/accountService";
import {Container, List, ListItem, Paper, Typography} from "@mui/material";
import Header from "../components/Header";

function HomePage() {
    const [customers, setCustomers] = useState<{ id: number; name: string; email: string }[]>([]);
    const [accounts, setAccounts] = useState<{
        id: number;
        type: string;
        balance: number;
        name: string;
        email: string
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
            <Header/>
            <Container>
                <Typography variant="h3" gutterBottom>
                    Dashboard
                </Typography>
                <Typography variant="h4" gutterBottom>
                    Customers
                </Typography>
                <List>
                    {customers.map((c) => (
                        <ListItem key={c.id}>
                            <Paper style={{padding: "10px", width: "100%"}}>
                                {c.name} - {c.email}
                            </Paper>
                        </ListItem>
                    ))}
                </List>
                <Typography variant="h4" gutterBottom>
                    Accounts
                </Typography>
                <List>
                    {accounts.map((a) => (
                        <ListItem key={a.id}>
                            <Paper style={{padding: "10px", width: "100%"}}>
                                {a.type} - ${a.balance} - {a.name} - {a.email}
                            </Paper>
                        </ListItem>
                    ))}
                </List>
            </Container>
        </div>
    );
}

export default HomePage;