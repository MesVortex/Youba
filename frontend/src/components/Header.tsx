import { AppBar, Toolbar, Typography, IconButton, Button } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";

function Header() {
    return (
        <AppBar position="static">
            <Toolbar>
                <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Youba Dashboard
                </Typography>
                <Button color="inherit" href="/">Home</Button>
                <Button color="inherit" href="/customers">Customers</Button>
                <Button color="inherit" href="/accounts">Accounts</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Header;