import { AppBar, Toolbar, Typography, IconButton, Button } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import HomeIcon from '@mui/icons-material/Home';
import AddCircleIcon from '@mui/icons-material/AddCircle';

function Header() {
    return (
        <AppBar position="fixed" sx={{ backgroundColor: "#ff3d00" }}>
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    Youba
                </Typography>
                <Button color="inherit" href="/" startIcon={<HomeIcon />}>Home</Button>
                <Button color="inherit" href="/customers" startIcon={<AddCircleOutlineIcon />}>Add Customers</Button>
                <Button color="inherit" href="/accounts" startIcon={<AddCircleIcon />}>Add Accounts</Button>
            </Toolbar>
        </AppBar>
    );
}

export default Header;